package com.wally.database.entity

import androidx.room.Entity

@Entity(tableName = "photos", primaryKeys = ["id"])
data class Photo(
    val id: String,
    val author: String,
    val download_url: String,
    val height: Int,
    val url: String,
    val width: Int,
    val bookmarked: Boolean,
)