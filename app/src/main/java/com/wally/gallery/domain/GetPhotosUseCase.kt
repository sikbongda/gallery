package com.wally.gallery.domain

import androidx.paging.PagingData
import com.wally.gallery.data.PhotoRepository
import com.wally.network.response.Photo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    operator fun invoke(): Flow<PagingData<Photo>> {
        return repository.getPhotoResultStream()
    }
}