package com.wally.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wally.database.entity.RemoteKey

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKey)

    @Query("SELECT * FROM remote_keys")
    suspend fun remoteKeyByQuery(): RemoteKey

    @Query("DELETE FROM remote_keys")
    suspend fun deleteByQuery()
}