package com.wally.network.response

import kotlinx.serialization.Serializable

@Serializable
data class PhotoResponse(
    val author: String,
    val download_url: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)