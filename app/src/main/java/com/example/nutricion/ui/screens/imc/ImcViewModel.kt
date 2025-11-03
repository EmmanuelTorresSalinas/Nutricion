package com.example.nutricion.ui.screens.imc

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.nutricion.data.model.UserData

class ImcViewModel : ViewModel() {

    private val _imc = mutableStateOf(0.0)
    val imc: State<Double> = _imc

    private val _clasificacion = mutableStateOf("")
    val clasificacion: State<String> = _clasificacion

    private val _calorias = mutableStateOf(0.0)
    val calorias: State<Double> = _calorias

    fun calcularIMC(user: UserData) {
        val resultado = user.peso / (user.estatura * user.estatura)
        _imc.value = String.format("%.2f", resultado).toDouble()

        _clasificacion.value = when {
            resultado < 18.5 -> "Bajo peso"
            resultado < 24.9 -> "Normal"
            resultado < 29.9 -> "Sobrepeso"
            else -> "Obesidad"
        }
    }

    fun calcularCalorias(user: UserData) {
        // Fórmula de Mifflin-St Jeor (Tasa Metabólica Basal)
        val tmb = if (user.sexo == "Hombre") {
            10 * user.peso + 6.25 * (user.estatura * 100) - 5 * user.edad + 5
        } else {
            10 * user.peso + 6.25 * (user.estatura * 100) - 5 * user.edad - 161
        }

        val factorActividad = when (user.nivelActividad) {
            "Sedentario" -> 1.2
            "Ligero" -> 1.375
            "Moderado" -> 1.55
            "Activo" -> 1.725
            "Muy activo" -> 1.9
            else -> 1.2
        }

        _calorias.value = String.format("%.2f", tmb * factorActividad).toDouble()
    }
}
