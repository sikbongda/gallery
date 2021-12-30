package com.wally.gallery.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wally.network.PhotoApiService
import com.wally.network.response.Photo

class PhotoPagingSource(
    private val photoApiService: PhotoApiService,
) : PagingSource<Int, Photo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = photoApiService.getListImages(nextPageNumber, 15)
            return LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextPageNumber.plus(1)
            )
        } catch (e: Exception) {
            // TODO: Exception handling
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}