package com.wally.gallery.data

import androidx.paging.PagingData
import com.wally.database.entity.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun getPhotoResultStream(): Flow<PagingData<Photo>>
}