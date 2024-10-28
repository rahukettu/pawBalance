package com.pawBalance.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pawBalance.ui.theme.PawBalanceTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enable edge-to-edge layout (optional)
        setContent {
            PawBalanceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PawBalanceNavHost(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Navigation composable function
@Composable
fun PawBalanceNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    // Navigation host with different screens
    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") { MainScreen(navController) }
        composable("infoScreen") { InfoScreen(navController) }
        composable("activityScreen") { ActivityScreen(navController) }
        composable("nutritionScreen") { NutritionScreen(navController) }
        composable("healthScreen") { HealthScreen(navController) }
        composable("addDogScreen") { AddDogScreen(navController)}
        composable( "myDogsScreen") {MyDogsScreen(navController)}
    }
}



// Preview function for design-time preview
@Preview(showBackground = true)
@Composable
fun PawBalancePreview() {
    PawBalanceTheme {
        // Show a preview of the navigation host (could be the main screen)
        PawBalanceNavHost()
    }
}
