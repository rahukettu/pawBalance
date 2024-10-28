// NutritionScreen.kt
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
import com.pawBalance.viewmodels.NutritionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutritionScreen(navController: NavController, nutritionViewModel: NutritionViewModel = viewModel()) {
    // Observing the food entries from ViewModel
    val foodEntries by nutritionViewModel.foodEntries.observeAsState(emptyList())

    var newFoodEntry by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Nutrition Tracker", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(20.dp))

        // Food Entry Input
        OutlinedTextField(
            value = newFoodEntry,
            onValueChange = { newFoodEntry = it },
            label = { Text("Enter Food Item") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            if (newFoodEntry.isNotBlank()) {
                nutritionViewModel.addFoodEntry(newFoodEntry)
                newFoodEntry = "" // Clear the input after submission
            }
        }) {
            Text("Add Food Item")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Displaying Food Entries
        Text(text = "Food Entries:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(10.dp))

        foodEntries.forEach { food ->
            Text(text = "- $food")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Clear All Entries Button
        Button(onClick = { nutritionViewModel.clearFoodEntries() }) {
            Text("Clear All Entries")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Back to Main Screen Button
        Button(onClick = { navController.navigate("mainScreen") }) {
            Text("Back to Main Screen")
        }
    }
}
