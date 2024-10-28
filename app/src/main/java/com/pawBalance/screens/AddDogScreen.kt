package com.pawBalance.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pawBalance.models.Dog
import com.pawBalance.viewmodels.DogViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDogScreen(navController: NavController, dogViewModel: DogViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var breed by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var isNeutered by remember { mutableStateOf(false) }
    var isVaccinated by remember { mutableStateOf(false) }
    var isVaccinatedUntil by remember { mutableStateOf("") }
    var microchipId by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Add Your Dog", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Dog Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = breed,
            onValueChange = { breed = it },
            label = { Text("Breed") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age (Years)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isNeutered,
                onCheckedChange = { isNeutered = it }
            )
            Text(text = "Is Neutered")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isVaccinated,
                onCheckedChange = { isVaccinated = it }
            )
            Text(text = "Is Vaccinated")
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (isVaccinated) {
            OutlinedTextField(
                value = isVaccinatedUntil,
                onValueChange = { isVaccinatedUntil = it },
                label = { Text("Vaccinated Until (YYYY-MM-DD)") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = microchipId,
            onValueChange = { microchipId = it },
            label = { Text("Microchip ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = dateOfBirth,
            onValueChange = { dateOfBirth = it },
            label = { Text("Date of Birth (YYYY-MM-DD)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            if (name.isNotBlank() && breed.isNotBlank() && age.isNotBlank() && weight.isNotBlank()) {
                val newDog = Dog(
                    name = name,
                    breed = breed,
                    age = age.toInt(),
                    weight = weight.toFloat(),
                    isNeutered = isNeutered,
                    isVaccinated = isVaccinated,
                    isVaccinatedUntil = if (isVaccinatedUntil.isNotBlank()) LocalDate.parse(isVaccinatedUntil) else null,
                    microchipId = if (microchipId.isNotBlank()) microchipId else null,
                    dateOfBirth = if (dateOfBirth.isNotBlank()) LocalDate.parse(dateOfBirth) else null
                )
                dogViewModel.addDog(newDog)
                navController.navigate("myDogsScreen")
            }
        }) {
            Text("Add Dog")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navController.navigate("mainScreen") }) {
            Text("Back to Main Screen")
        }
    }
}
