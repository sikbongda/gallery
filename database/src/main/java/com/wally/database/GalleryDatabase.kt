package com.wally.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.withTransaction
import com.wally.database.dao.PhotoDao
import com.wally.database.dao.RemoteKeyDao
import com.wally.database.entity.Photo
import com.wally.database.entity.RemoteKey

@Database(
    entities = [
        Photo::class,
        RemoteKey::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class GalleryDatabase : RoomDatabase() {
    abstract fun getPhotoDao(): PhotoDao
    abstract fun remoteKeyDao(): RemoteKeyDao

    companion object {
        fun create(context: Context, useInMemory: Boolean): GalleryDatabase {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, GalleryDatabase::class.java)
            } else {
                Room.databaseBuilder(context, GalleryDatabase::class.java, "gallery.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}

suspend fun <R> GalleryDatabase.withTransaction(block: suspend () -> R): R {
    return withTransaction { block() }
}