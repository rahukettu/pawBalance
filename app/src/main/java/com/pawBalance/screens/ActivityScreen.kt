package com.pawBalance.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pawBalance.viewmodels.ActivityViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.pawBalance.models.Activity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityScreen(
    navController: NavController,
    activityViewModel: ActivityViewModel = hiltViewModel()
) {
    val activities by activityViewModel.activities.observeAsState(emptyList())
    var selectedActivityType by remember { mutableStateOf("Walking") }
    var activityDescription by remember { mutableStateOf("") }
    var selectedIntensity by remember { mutableStateOf("Mild") }
    var expanded by remember { mutableStateOf(false) } // State for dropdown menu visibility

    // Lists for activity types and intensities
    val activityTypes = listOf("Walking", "Agility", "Playing")
    val intensities = listOf("Mild", "Moderate", "Heavy")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Text(text = "Dog Activities", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(20.dp))
        }

        if (activities.isEmpty()) {
            item {
                Text(text = "No activities found.", style = MaterialTheme.typography.bodyMedium)
            }
        } else {
            items(activities) { activity ->
                ActivityItem(activity = activity)
            }
        }

        item {
            Text(text = "Select Activity Type", style = MaterialTheme.typography.bodyLarge)

            // Trigger for dropdown menu
            Box(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Button(onClick = { expanded = true }) {
                    Text(selectedActivityType)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    activityTypes.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type) },
                            onClick = {
                                selectedActivityType = type
                                expanded = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            TextField(
                value = activityDescription,
                onValueChange = { activityDescription = it },
                label = { Text("Activity Description") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Intensity selection
            Text(text = "Select Activity Intensity", style = MaterialTheme.typography.bodyLarge)
            intensities.forEach { intensity ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    RadioButton(
                        selected = (selectedIntensity == intensity),
                        onClick = { selectedIntensity = intensity }
                    )
                    Text(text = intensity)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                activityViewModel.addActivity(selectedActivityType, activityDescription, selectedIntensity)
                activityDescription = "" // Reset
            }) {
                Text("Add Activity")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { navController.navigate("mainScreen") }) {
                Text("Back to Main Screen")
            }
        }
    }
}

@Composable
fun ActivityItem(activity: Activity) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = activity.type, style = MaterialTheme.typography.bodyLarge)
        Text(text = activity.description, style = MaterialTheme.typography.bodyMedium)
        Text(text = "Intensity: ${activity.intensity}", style = MaterialTheme.typography.bodySmall)
        Text(text = activity.timestamp, style = MaterialTheme.typography.bodySmall)
    }
}
