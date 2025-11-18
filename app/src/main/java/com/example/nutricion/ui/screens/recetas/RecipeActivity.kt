package com.example.nutricion.ui.screens.recetas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.nutricion.R
import com.example.nutricion.data.RecipeDatabase
import com.example.nutricion.data.RecipeEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeActivity : AppCompatActivity() {

    private lateinit var category: String
    private var index: Int = 1 // 1..3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        category = intent.getStringExtra("category") ?: "desayuno"
        index = intent.getIntExtra("index", 1)

        // Seleccionar layout según category e index
        val layoutId = when (category) {
            "desayuno" -> when (index) {
                1 -> R.layout.activity_desayuno_1
                2 -> R.layout.activity_desayuno_2
                else -> R.layout.activity_desayuno_3
            }
            "comida" -> when (index) {
                1 -> R.layout.activity_comida_1
                2 -> R.layout.activity_comida_2
                else -> R.layout.activity_comida_3
            }
            "cena" -> when (index) {
                1 -> R.layout.activity_cena_1
                2 -> R.layout.activity_cena_2
                else -> R.layout.activity_cena_3
            }
            else -> R.layout.activity_desayuno_1
        }

        setContentView(layoutId)

        bindDataFromDatabaseIfAvailable()

        val btnPrev = findViewById<Button?>(R.id.btnPrev)
        val btnNext = findViewById<Button?>(R.id.btnNext)
        val btnComidas = findViewById<Button?>(R.id.btnComidas)

        btnPrev?.setOnClickListener {
            if (index > 1) abrirOtra(index - 1)
        }

        btnNext?.setOnClickListener {
            if (index < 3) abrirOtra(index + 1)
        }

        btnComidas?.setOnClickListener {
            startActivity(Intent(this, RecetarioMenuActivity::class.java))
            finish()
        }

        btnPrev?.visibility = if (index > 1) View.VISIBLE else View.GONE
        btnNext?.visibility = if (index < 3) View.VISIBLE else View.GONE
    }

    private fun abrirOtra(newIndex: Int) {
        val intent = Intent(this, RecipeActivity::class.java)
        intent.putExtra("category", category)
        intent.putExtra("index", newIndex)
        startActivity(intent)
        finish()
    }

    private fun bindDataFromDatabaseIfAvailable() {
        val db = RecipeDatabase.getDatabase(applicationContext)
        val dao = db.recipeDao()

        CoroutineScope(Dispatchers.IO).launch {
            val list: List<RecipeEntity>? = dao.getByCategoryOnce(category)

            if (!list.isNullOrEmpty()) {
                val safeList = list
                val idx = (index - 1).coerceIn(0, safeList.size - 1)
                val recipe = safeList[idx]

                runOnUiThread {
                    applyRecipeToViews(recipe)
                }
            }
        }
    }

    private fun applyRecipeToViews(recipe: RecipeEntity) {
        val txtTitle = findViewById<TextView?>(R.id.txtTitle)
        val txtIngredients = findViewById<TextView?>(R.id.txtIngredients)
        val txtCalorias = findViewById<TextView?>(R.id.txtCalorias)
        val txtPreparation = findViewById<TextView?>(R.id.txtPreparation)

        txtTitle?.text = recipe.title
        txtIngredients?.text = "Ingredientes:\n${recipe.ingredients}"
        txtCalorias?.text = "Calorías: ${recipe.calories} kcal"
        txtPreparation?.text = "Preparación:\n${recipe.preparation}"
    }
}