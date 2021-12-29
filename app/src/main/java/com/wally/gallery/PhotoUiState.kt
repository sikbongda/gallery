package com.wally.gallery

import com.wally.network.response.Photo

sealed class PhotoUiState {
    object Initial : PhotoUiState()
    object Loading : PhotoUiState()
    data class Success(val photo: List<Photo>) : PhotoUiState()
    data class Error(val message: String) : PhotoUiState()
}