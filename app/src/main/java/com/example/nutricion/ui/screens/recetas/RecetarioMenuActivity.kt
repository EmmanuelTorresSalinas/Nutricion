package com.example.nutricion.ui.screens.recetas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.nutricion.MainActivity
import com.example.nutricion.R
import com.example.nutricion.data.DatabaseInitializer

class RecetarioMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recetario_menu)

        // Inicializar DB (semilla) - opcional si ya lo hiciste en MainActivity
        //DatabaseInitializer.initialize(this)

        val btnDesayuno = findViewById<Button>(R.id.btnDesayuno)
        val btnComida = findViewById<Button>(R.id.btnComida)
        val btnCena = findViewById<Button>(R.id.btnCena)
        val btnMenuPrincipal = findViewById<Button>(R.id.btnMenuPrincipal)

        btnDesayuno.setOnClickListener {
            abrirReceta("desayuno", 1)
        }

        btnComida.setOnClickListener {
            abrirReceta("comida", 1)
        }

        btnCena.setOnClickListener {
            abrirReceta("cena", 1)
        }

        btnMenuPrincipal.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

    }

    private fun abrirReceta(category: String, index: Int) {
        val intent = Intent(this, RecipeActivity::class.java)
        intent.putExtra("category", category)
        intent.putExtra("index", index) // 1,2 o 3
        startActivity(intent)
    }
}