// NutritionViewModel.kt
package com.pawBalance.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NutritionViewModel : ViewModel() {
    // MutableLiveData to hold nutrition data
    private val _foodEntries = MutableLiveData<List<String>>(emptyList())
    val foodEntries: LiveData<List<String>> get() = _foodEntries

    // Function to add food entry
    fun addFoodEntry(food: String) {
        val currentList = _foodEntries.value ?: emptyList()
        _foodEntries.value = currentList + food
    }

    // Function to clear all food entries
    fun clearFoodEntries() {
        _foodEntries.value = emptyList()
    }
}


