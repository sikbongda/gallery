package com.wally.gallery.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wally.database.entity.Photo
import com.wally.gallery.domain.GetPhotosUseCase
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

    val photoFlow: Flow<PagingData<Photo>> = getPhotosUseCase().cachedIn(viewModelScope)
}