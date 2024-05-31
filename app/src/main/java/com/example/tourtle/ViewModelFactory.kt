package com.example.tourtle

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tourtle.data.repository.ForumRepository
import com.example.tourtle.data.repository.UserRepository
import com.example.tourtle.di.Injection
import com.example.tourtle.ui.forum.ForumViewModel
import com.example.tourtle.ui.home.HomeViewModel
import com.example.tourtle.ui.login.LoginViewModel
import com.example.tourtle.ui.profile.ProfileViewModel

class ViewModelFactory(
    private val userRepository: UserRepository,
    private val forumRepository: ForumRepository,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(ForumViewModel::class.java) -> {
                ForumViewModel(userRepository, forumRepository) as T
            }
//            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
//                DetailViewModel(userRepository, storyRepository) as T
//            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    val userRepository = Injection.provideUserRepository(context)
                    val forumRepository = Injection.provideForumRepository(context)
                    INSTANCE = ViewModelFactory(userRepository, forumRepository)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}