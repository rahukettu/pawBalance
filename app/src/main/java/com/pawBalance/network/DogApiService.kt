package com.pawBalance.network
import com.pawBalance.models.BreedsResponse
import com.pawBalance.models.DogImageResponse
import retrofit2.Call
import retrofit2.http.GET

interface DogApiService {

    // Get a random image
    @GET("breeds/image/random")
    fun getRandomDogImage(): Call<DogImageResponse>

    // Get a list of all breeds
    @GET("breeds/list/all")
    fun getAllBreeds(): Call<BreedsResponse>
}

