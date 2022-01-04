package com.wally.gallery.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wally.database.entity.Photo
import com.wally.gallery.domain.GetPhotosUseCase
import com.wally.gallery.domain.SetBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val setBookmarkUseCase: SetBookmarkUseCase,
) : ViewModel() {
    val photoFlow: Flow<PagingData<Photo>> = getPhotosUseCase().cachedIn(viewModelScope)

    fun setBookmark(photoId: String, bookmarked: Boolean) {
        viewModelScope.launch {
            setBookmarkUseCase(photoId, bookmarked)
        }
    }
}