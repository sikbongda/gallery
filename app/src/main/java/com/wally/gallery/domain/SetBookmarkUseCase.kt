package com.wally.gallery.domain

import com.wally.gallery.data.PhotoRepository
import javax.inject.Inject

class SetBookmarkUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(photoId: String, bookmarked: Boolean) {
        repository.setBookmark(photoId, bookmarked)
    }
}