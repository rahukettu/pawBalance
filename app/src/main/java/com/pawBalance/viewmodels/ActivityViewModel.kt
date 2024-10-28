package com.pawBalance.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pawBalance.models.Activity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ActivityViewModel : ViewModel() {
        private val _activities = MutableLiveData<List<Activity>>(emptyList())
        val activities: LiveData<List<Activity>> = _activities

        fun addActivity(type: String, description: String, intensity: String) {
            val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            val newActivity = Activity(type, description, timestamp, intensity)
            _activities.value = _activities.value?.plus(newActivity)
        }
    }