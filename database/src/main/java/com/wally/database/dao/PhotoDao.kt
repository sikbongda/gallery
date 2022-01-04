package com.wally.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wally.database.entity.Photo

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(users: List<Photo>)

    @Query("SELECT * FROM photos")
    fun selectAllPhotos(): PagingSource<Int, Photo>

    @Query("DELETE FROM photos")
    suspend fun clearAll()

    @Query("UPDATE photos SET bookmarked = :bookmarked WHERE id = :photoId")
    suspend fun updateBookmark(photoId: String, bookmarked: Boolean)
}