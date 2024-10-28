package com.pawBalance.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pawBalance.models.Dog
import com.pawBalance.viewmodels.DogViewModel

@Composable
fun MyDogsScreen(navController: NavController, dogViewModel: DogViewModel = viewModel()) {
    val dogs by dogViewModel.dogs.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "My Dogs", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(20.dp))

        if (dogs.isEmpty()) {
            Text("You have no dogs added.")
        } else {
            dogs.forEach { dog ->
                DogItem(dog)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navController.navigate("addDogScreen") }) {
            Text("Add Another Dog")
        }
    }
}

@Composable
fun DogItem(dog: Dog) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text("Name: ${dog.name}", style = MaterialTheme.typography.bodyMedium)
        Text("Breed: ${dog.breed}", style = MaterialTheme.typography.bodyMedium)
        Text("Age: ${dog.age} years", style = MaterialTheme.typography.bodyMedium)
        Text("Weight: ${dog.weight} kg", style = MaterialTheme.typography.bodyMedium)
        // Add other fields as necessary
        Spacer(modifier = Modifier.height(10.dp))
    }
}