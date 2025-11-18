package com.example.nutricion.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val ingredients: String,
    val preparation: String,
    val calories: Int,
    val category: String // "desayuno", "comida", "cena"
)