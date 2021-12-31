package com.wally.gallery.ui

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.wally.gallery.domain.GetPhotosUseCase
import com.wally.network.response.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {
    private val _photoUiState: MutableStateFlow<PhotoUiState> =
        MutableStateFlow(PhotoUiState.Initial)
    val photoUiState: StateFlow<PhotoUiState> = _photoUiState

    val photoFlow: Flow<PagingData<Photo>> = getPhotosUseCase()
}