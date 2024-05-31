package com.example.tourtle.di

import android.content.Context
import com.example.tourtle.data.pref.UserPreference
import com.example.tourtle.data.pref.dataStore
import com.example.tourtle.data.repository.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }

//    fun provideStoryRepository(context: Context): StoryRepository {
//        val pref = UserPreference.getInstance(context.dataStore)
//        val user = runBlocking { pref.getSession().first() }
//        val apiService = ApiConfig.getApiService(user.token)
//        val database = StoryDatabase.getDatabase(context)
//        return StoryRepository.getInstance(database, apiService)
//    }

}