// DogViewModel.kt
package com.pawBalance.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pawBalance.models.Dog

class DogViewModel : ViewModel() {
    // MutableLiveData to hold the list of dogs
    private val _dogs = MutableLiveData<List<Dog>>(emptyList())
    val dogs: LiveData<List<Dog>> get() = _dogs

    fun addDog(dog: Dog) {
        val currentDogs = _dogs.value?.toMutableList() ?: mutableListOf()
        currentDogs.add(dog)
        _dogs.value = currentDogs
    }

    // Function to clear all dogs
    fun clearDogs() {
        _dogs.value = emptyList()
    }
}
