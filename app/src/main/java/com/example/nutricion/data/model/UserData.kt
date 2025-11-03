package com.example.nutricion.data.model

data class UserData(
    val peso: Double,       // en kilogramos
    val estatura: Double,   // en metros
    val edad: Int,          // en años
    val sexo: String,       // "Hombre" o "Mujer"
    val nivelActividad: String // "Sedentario", "Ligero", "Moderado", "Activo", "Muy activo"
)