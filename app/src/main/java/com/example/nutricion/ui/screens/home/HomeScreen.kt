package com.example.nutricion.ui.screens.home

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nutricion.ui.screens.recetas.RecetarioMenuActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val context = LocalContext.current  // Necesario para abrir Activities XML.

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nutrición App") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenido 👋",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Selecciona una opción:",
                style = MaterialTheme.typography.bodyLarge
            )

            // 🔥 BOTÓN CORREGIDO: abre la Activity XML de Recetario
            Button(
                onClick = {
                    val intent = Intent(context, RecetarioMenuActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("🍽️ Recetario")
            }

            Button(
                onClick = { navController.navigate("imc") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("📊 Cálculo de IMC")
            }

            Button(
                onClick = { navController.navigate("calories") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("🔥 Calorías consumidas")
            }

            Button(
                onClick = { navController.navigate("favorites") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("❤️ Recetas favoritas")
            }
        }
    }
}

