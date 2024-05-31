package com.example.tourtle.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tourtle.data.api.response.ListForumItem

@Dao
interface ForumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForum(quote: List<ListForumItem>)

    @Query("SELECT * FROM forum")
    fun getAllForum(): PagingSource<Int, ListForumItem>

    @Query("DELETE FROM forum")
    suspend fun deleteAll()
}