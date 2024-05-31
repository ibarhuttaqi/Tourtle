package com.example.tourtle.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.tourtle.data.api.response.ListForumItem
import com.example.tourtle.data.api.retrofit.ApiConfig
import com.example.tourtle.data.api.retrofit.ApiService
import com.example.tourtle.data.database.ForumDatabase
import com.example.tourtle.data.database.ForumRemoteMediator

class ForumRepository(private val forumDatabase: ForumDatabase, private val apiService: ApiService) {

    fun getForum(token: String): LiveData<PagingData<ListForumItem>> {
        val service = ApiConfig.getApiService(token)
        Log.e("TRY FORUM REPO", "$apiService")
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            remoteMediator = ForumRemoteMediator(forumDatabase, service),
            pagingSourceFactory = {
                forumDatabase.forumDao().getAllForum()
            }
        ).liveData
    }

    companion object {
        @Volatile
        private var instance: ForumRepository? = null

        fun getInstance(forumDatabase: ForumDatabase, apiService: ApiService): ForumRepository =
            instance ?: synchronized(this) {
                instance ?: ForumRepository(forumDatabase, apiService).also { instance = it }
            }
    }
}