package com.example.nutricion.ui.screens.imc

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nutricion.data.model.UserData

@Composable
fun ImcScreen(viewModel: ImcViewModel = viewModel()) {  // ✅ Aquí el paréntesis
    var peso by remember { mutableStateOf("") }
    var estatura by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("Hombre") }
    var nivelActividad by remember { mutableStateOf("Sedentario") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Cálculo de IMC y Calorías", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Peso (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = estatura,
            onValueChange = { estatura = it },
            label = { Text("Estatura (m)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad (años)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Selección de sexo con lista desplegable
        Text("Sexo:")
        DropdownMenuSelector(
            selected = sexo,
            options = listOf("Hombre", "Mujer"),
            onSelect = { sexo = it }
        )

        // Selección de nivel de actividad
        Text("Nivel de actividad:")
        DropdownMenuSelector(
            selected = nivelActividad,
            options = listOf("Sedentario", "Ligero", "Moderado", "Activo", "Muy activo"),
            onSelect = { nivelActividad = it }
        )

        Button(
            onClick = {
                val user = UserData(
                    peso.toDoubleOrNull() ?: 0.0,
                    estatura.toDoubleOrNull() ?: 0.0,
                    edad.toIntOrNull() ?: 0,
                    sexo,
                    nivelActividad
                )
                viewModel.calcularIMC(user)
                viewModel.calcularCalorias(user)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular")
        }

        if (viewModel.imc.value > 0) {
            Text("IMC: ${viewModel.imc.value}")
            Text("Clasificación: ${viewModel.clasificacion.value}")
            Text("Requerimiento calórico diario: ${viewModel.calorias.value} kcal")
        }
    }
}

@Composable
fun DropdownMenuSelector(selected: String, options: List<String>, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }) {
            Text(selected)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        onSelect(it)
                        expanded = false
                    }
                )
            }
        }
    }
}
