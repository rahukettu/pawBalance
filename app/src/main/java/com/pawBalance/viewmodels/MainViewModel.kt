package com.pawBalance.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel : ViewModel() {
    private val _dogActivities = MutableLiveData<List<String>>(emptyList())
    val dogActivities: LiveData<List<String>> get() = _dogActivities

    fun addActivity(activity: String) {
        _dogActivities.value = _dogActivities.value?.plus(activity)
    }
}


