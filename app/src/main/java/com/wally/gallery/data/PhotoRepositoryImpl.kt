package com.wally.gallery.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wally.network.PhotoApiService
import com.wally.network.response.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photoApiService: PhotoApiService
) : PhotoRepository {
    override fun getPhotoResultStream(): Flow<PagingData<Photo>> =
        Pager(
            config = PagingConfig(pageSize = 15, enablePlaceholders = false),
            pagingSourceFactory = { PhotoPagingSource(photoApiService) }
        ).flow
}