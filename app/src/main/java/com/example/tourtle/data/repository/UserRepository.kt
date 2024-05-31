package com.example.tourtle.data.repository

import com.example.tourtle.data.api.retrofit.ApiConfig
import com.example.tourtle.data.api.response.LoginResponse
import com.example.tourtle.data.pref.UserModel
import com.example.tourtle.data.pref.UserPreference
import kotlinx.coroutines.flow.Flow

class UserRepository private constructor(
    private val userPreference: UserPreference
) {

    suspend fun login(email: String, password: String): LoginResponse {
        val apiService = ApiConfig.getApiService("")
        return apiService.login(email, password)
    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference)
            }.also { instance = it }
    }
}