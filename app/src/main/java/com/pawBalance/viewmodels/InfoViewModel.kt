package com.pawBalance.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pawBalance.network.DogApiService
import com.pawBalance.models.DogImageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InfoViewModel : ViewModel() {
    private val _infoText = MutableLiveData<String>("Dog Care Information")
    val infoText: LiveData<String> get() = _infoText

    private val _dogImage = MutableLiveData<String>()
    val dogImage: LiveData<String> get() = _dogImage

    private val dogApiService: DogApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        dogApiService = retrofit.create(DogApiService::class.java)
    }

    fun updateInfo(newInfo: String) {
        if (newInfo.isNotBlank()) {
            _infoText.value = newInfo
        }
    }

    fun fetchRandomDogImage() {
        dogApiService.getRandomDogImage().enqueue(object : Callback<DogImageResponse> {
            override fun onResponse(call: Call<DogImageResponse>, response: retrofit2.Response<DogImageResponse>) {
                if (response.isSuccessful) {
                    _dogImage.value = response.body()?.message // Set the dog image URL
                }
            }

            override fun onFailure(call: Call<DogImageResponse>, t: Throwable) {
                _dogImage.value = "Error fetching image" // Handle the error
            }
        })
    }
}
