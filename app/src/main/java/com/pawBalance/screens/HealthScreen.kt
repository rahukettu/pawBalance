package com.pawBalance.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pawBalance.viewmodels.HealthViewModel



    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HealthScreen(navController: NavController, healthViewModel: HealthViewModel = viewModel()) {

        val weight by healthViewModel.weight.observeAsState(0f)
        val vaccinationStatus by healthViewModel.vaccinationStatus.observeAsState("Not vaccinated")
        val checkUpRecords by healthViewModel.checkUpRecords.observeAsState(emptyList())

        var newWeight by remember { mutableStateOf("") }
        var newVaccinationStatus by remember { mutableStateOf("") }
        var newCheckUpRecord by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = "Health Tracker", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(20.dp))

            // Weight Input Field
            OutlinedTextField(
                value = newWeight,
                onValueChange = { newWeight = it },
                label = { Text("Enter Weight (kg)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                val weightValue = newWeight.toFloatOrNull()
                if (weightValue != null && weightValue > 0) {
                    healthViewModel.updateWeight(weightValue)
                    newWeight = "" // Clear
                }
            }) {
                Text("Update Weight")
            }

            Spacer(modifier = Modifier.height(20.dp))


            OutlinedTextField(
                value = newVaccinationStatus,
                onValueChange = { newVaccinationStatus = it },
                label = { Text("Enter Vaccination Status") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                healthViewModel.updateVaccinationStatus(newVaccinationStatus)
                newVaccinationStatus = "" // Clear
            }) {
                Text("Update Vaccination Status")
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = newCheckUpRecord,
                onValueChange = { newCheckUpRecord = it },
                label = { Text("Enter Check-Up Record") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                if (newCheckUpRecord.isNotBlank()) {
                    healthViewModel.addCheckUpRecord(newCheckUpRecord)
                    newCheckUpRecord = ""
                }
            }) {
                Text("Add Check-Up Record")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Display Current Health Information
            Text(text = "Current Weight: ${weight} kg", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Vaccination Status: $vaccinationStatus", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Check-Up Records:", style = MaterialTheme.typography.bodyLarge)
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(checkUpRecords) { record ->
                    Text(text = record, style = MaterialTheme.typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Back to Main Screen Button
            Button(onClick = { navController.navigate("mainScreen") }) {
                Text("Back to Main Screen")
            }
        }
    }
