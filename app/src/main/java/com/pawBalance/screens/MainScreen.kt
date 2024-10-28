package com.pawBalance.screens

import RetrofitClient
import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.Glide
import com.pawBalance.models.DogImageResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun MainScreen(navController: NavController) {
    var dogImageUrl by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(true) }
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // Load random dog image on first launch
    LaunchedEffect(Unit) {
        fetchRandomDogImage { url ->
            dogImageUrl = url
            loading = false

            // Launch coroutine to load image when URL is fetched
            url?.let {
                coroutineScope.launch {
                    imageBitmap = loadImage(context, it)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display Dog Image
        if (loading) {
            Text("Loading...")
        } else {
            imageBitmap?.let {
                Image(bitmap = it.asImageBitmap(), contentDescription = "Dog Image")
            } ?: Text("Failed to load image.")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button to Fetch New Dog Image
        Button(onClick = {
            loading = true
            fetchRandomDogImage { url ->
                dogImageUrl = url
                loading = false

                // Launch coroutine to load image when button is clicked
                url?.let {
                    coroutineScope.launch {
                        imageBitmap = loadImage(context, it)
                    }
                }
            }
        }) {
            Text("Fetch New Dog Image")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Navigation Buttons
        NavigationButtons(navController)
    }
}

@Composable
fun NavigationButtons(navController: NavController) {
    Column {
        Button(onClick = { navController.navigate("infoScreen") }) {
            Text("Info")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("activityScreen") }) {
            Text("Activities")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("nutritionScreen") }) {
            Text("Nutrition")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("healthScreen") }) {
            Text("Health")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("addDogScreen") }) {
            Text("Add Dog")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("myDogsScreen") }) {
            Text("My Dogs")
        }
    }
}

private fun fetchRandomDogImage(onResult: (String?) -> Unit) {
    val service = RetrofitClient.dogApiService
    service.getRandomDogImage().enqueue(object : Callback<DogImageResponse> {
        override fun onResponse(call: Call<DogImageResponse>, response: Response<DogImageResponse>) {
            if (response.isSuccessful) {
                onResult(response.body()?.message)
            } else {
                onResult(null)
            }
        }

        override fun onFailure(call: Call<DogImageResponse>, t: Throwable) {
            onResult(null)
        }
    })
}

// Load image using Glide with context parameter
private suspend fun loadImage(context: Context, url: String): Bitmap? {
    return withContext(Dispatchers.IO) {
        Glide.with(context)
            .asBitmap()
            .load(url)
            .submit()
            .get()
    }
}

// Preview function
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(navController = rememberNavController())
}

