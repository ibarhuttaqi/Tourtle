package com.example.tourtle.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tourtle.data.api.response.LoginResponse
import com.example.tourtle.data.pref.UserModel
import com.example.tourtle.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    suspend fun login(email: String, password: String): LoginResponse {
        return repository.login(email, password)
    }
}