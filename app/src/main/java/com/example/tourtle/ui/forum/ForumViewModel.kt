package com.example.tourtle.ui.forum

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tourtle.data.api.response.ListForumItem
import com.example.tourtle.data.pref.UserModel
import com.example.tourtle.data.repository.ForumRepository
import com.example.tourtle.data.repository.UserRepository

class ForumViewModel(private val userRepository: UserRepository, private val forumRepository: ForumRepository) : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is forum Fragment"
//    }
//    val text: LiveData<String> = _text

//    private val _token = MutableLiveData<String>()
//    val token: LiveData<String> get() = _token

    fun getSession(): LiveData<UserModel> {
        return userRepository.getSession().asLiveData()
    }

    fun getForum(token: String): LiveData<PagingData<ListForumItem>> {
        Log.d("TRY FORUM VIEW MODEL", "$token")
        return forumRepository.getForum(token).cachedIn(viewModelScope)
    }
}