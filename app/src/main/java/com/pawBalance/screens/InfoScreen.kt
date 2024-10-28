package com.pawBalance.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pawBalance.viewmodels.InfoViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun InfoScreen(navController: NavController, infoViewModel: InfoViewModel = viewModel()) {
    val infoText by infoViewModel.infoText.observeAsState("")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = infoText, style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { infoViewModel.updateInfo("Updated Dog Care Info") }) {
            Text("Update Info")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navController.navigate("mainScreen") }) {
            Text("Back to Main Screen")
        }
    }
}