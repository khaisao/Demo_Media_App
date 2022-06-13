package com.prox.powerpointreader.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.model.MMediaFile

@Database(
    entities = [MMediaFile::class],
    exportSchema = false,
    version = 1
)
abstract class MediaFileDatabase:RoomDatabase() {
    abstract  fun mediaFileDao():MediaFileDao
        companion object {
        @Volatile
        private var instance: MediaFileDatabase? = null
        fun getInstance(context: Context): MediaFileDatabase = instance ?: synchronized(this) {
            Room.databaseBuilder(
                context,
                MediaFileDatabase::class.java,
                "media.db"
            ).build()
        }
    }
}