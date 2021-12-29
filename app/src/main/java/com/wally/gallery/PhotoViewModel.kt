package com.wally.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wally.network.PhotoApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PhotoViewModel : ViewModel() {
    private val _photoUiState: MutableStateFlow<PhotoUiState> =
        MutableStateFlow(PhotoUiState.Initial)
    val photoUiState: StateFlow<PhotoUiState> = _photoUiState

    fun getListImages() {
        viewModelScope.launch {
            runCatching {
                PhotoApiService.create().getListImages()
            }.onSuccess {
                _photoUiState.value = PhotoUiState.Success(it)
            }.onFailure {
                _photoUiState.value = PhotoUiState.Error(it.message.toString())
            }
        }
    }
}