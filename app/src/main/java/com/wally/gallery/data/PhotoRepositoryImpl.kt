package com.wally.gallery.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wally.database.GalleryDatabase
import com.wally.database.entity.Photo
import com.wally.network.PhotoApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val db: GalleryDatabase,
    private val photoApiService: PhotoApiService,
) : PhotoRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPhotoResultStream(): Flow<PagingData<Photo>> =
        Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            remoteMediator = PhotoRemoteMediator(db, photoApiService)
        ) {
            db.getPhotoDao().selectAllPhotos()
        }.flow

    override suspend fun setBookmark(photoId: String, bookmarked: Boolean) {
        Log.d("PhotoRepositoryImpl", "setBookmark: $bookmarked")
        db.getPhotoDao().updateBookmark(photoId, !bookmarked)
    }
}