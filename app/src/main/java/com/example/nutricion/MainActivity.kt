package com.example.nutricion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nutricion.ui.screens.home.HomeScreen
import com.example.nutricion.ui.screens.imc.ImcScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") { HomeScreen(navController) }
                    composable("imc") { ImcScreen() }

                    // Estas se crearán más adelante
                    composable("recipes") {
                        Text("Pantalla de Recetario (pendiente)")
                    }
                    composable("calories") {
                        Text("Pantalla de Calorías (pendiente)")
                    }
                    composable("favorites") {
                        Text("Pantalla de Favoritos (pendiente)")
                    }
                }
            }
        }
    }
}