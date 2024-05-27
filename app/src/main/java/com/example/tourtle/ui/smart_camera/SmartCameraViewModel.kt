package com.example.tourtle.ui.smart_camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SmartCameraViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is smart camera Fragment"
    }
    val text: LiveData<String> = _text
}