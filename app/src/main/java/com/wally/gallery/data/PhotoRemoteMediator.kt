package com.wally.gallery.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.bumptech.glide.load.HttpException
import com.wally.database.GalleryDatabase
import com.wally.database.entity.Photo
import com.wally.database.entity.RemoteKey
import com.wally.database.withTransaction
import com.wally.network.PhotoApiService
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PhotoRemoteMediator(
    private val database: GalleryDatabase,
    private val networkService: PhotoApiService,
) : RemoteMediator<Int, Photo>() {
    private val tag: String = "PhotoRemoteMediator"
    private val photoDao = database.getPhotoDao()
    private val remoteKeyDao = database.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Photo>
    ): MediatorResult {
        Log.d("PhotoRemoteMediator", "load: $loadType")
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = database.withTransaction {
                        remoteKeyDao.remoteKeyByQuery()
                    }
                    Log.d(tag, "remoteKey: $remoteKey")
                    remoteKey.nextKey // 서버 API 에서 nextKey 를 지원하지 않아서 DB에 nextKey 저장
                }
            }

            val response =
                networkService.getListImages(page = loadKey, limit = state.config.pageSize)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.deleteByQuery()
                    photoDao.clearAll()
                }

                // Update RemoteKey
                val nextKey = loadKey.plus(1)
                Log.d(tag, "nextKey: $nextKey")
                remoteKeyDao.insertOrReplace(RemoteKey("dummy", nextKey))

                Log.d(tag, "insertPhotos")
                photoDao.insertPhotos(response.map {
                    Photo(it.id, it.author, it.download_url, it.height, it.url, it.width, false)
                })
            }

            MediatorResult.Success(
                endOfPaginationReached = response.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}