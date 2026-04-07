package com.example.tiffo.viewmodel

import android.R
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {
    private val _navigate= MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean> = _navigate

    fun startTimer(){
        viewModelScope.launch {
            delay(5000)
            _navigate.postValue(true)
        }
    }
}