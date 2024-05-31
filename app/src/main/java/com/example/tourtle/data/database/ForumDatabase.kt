package com.example.tourtle.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tourtle.data.api.response.ListForumItem

@Database(
    entities = [ListForumItem::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class ForumDatabase : RoomDatabase() {

    abstract fun forumDao(): ForumDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var INSTANCE: ForumDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): ForumDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ForumDatabase::class.java, "forum_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}