package com.wally.gallery.ui

import com.wally.database.entity.Photo

sealed class PhotoUiState {
    object Initial : PhotoUiState()
    object Loading : PhotoUiState()
    data class Success(val photo: List<Photo>) : PhotoUiState()
    data class Error(val message: String) : PhotoUiState()
}