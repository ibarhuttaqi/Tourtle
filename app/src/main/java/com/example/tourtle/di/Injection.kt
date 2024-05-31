package com.example.tourtle.di

import android.content.Context
import com.example.tourtle.data.api.retrofit.ApiConfig
import com.example.tourtle.data.database.ForumDatabase
import com.example.tourtle.data.pref.UserPreference
import com.example.tourtle.data.pref.dataStore
import com.example.tourtle.data.repository.ForumRepository
import com.example.tourtle.data.repository.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }

    fun provideForumRepository(context: Context): ForumRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        val database = ForumDatabase.getDatabase(context)
        return ForumRepository.getInstance(database, apiService)
    }

}