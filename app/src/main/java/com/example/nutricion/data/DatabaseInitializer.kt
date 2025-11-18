package com.example.nutricion.data

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DatabaseInitializer {

    fun initialize(context: Context) {
        val db = RecipeDatabase.getDatabase(context)
        val dao = db.recipeDao()

        // Observación: usamos coroutine para insertar en background
        CoroutineScope(Dispatchers.IO).launch {
            // Nota: no usamos getAll().value aquí porque LiveData no se puede leer directamente en background sin observar.
            // Para evitar duplicados en ejecuciones repetidas, simplemente insertamos con REPLACE y dejamos que el id se regenere.
            val seed = listOf(
                // Desayunos
                RecipeEntity(
                    title = "Avena con manzana y canela",
                    ingredients = "1 taza de avena\n1 manzana picada\nCanela al gusto\n1 cda de almendras picadas",
                    preparation = "1. Cocinar la avena con leche descremada.\n2. Añadir manzana picada, canela y almendras.\n3. Servir caliente.",
                    calories = 320,
                    category = "desayuno"
                ),
                RecipeEntity(
                    title = "Omelette de claras con espinacas",
                    ingredients = "4 claras de huevo\n1 taza de espinacas\n1 tomate picado\n1 cdita de aceite de oliva",
                    preparation = "1. Saltear espinacas y tomate.\n2. Batir claras y cocinar en sartén antiadherente.\n3. Rellenar con las espinacas y servir.",
                    calories = 250,
                    category = "desayuno"
                ),
                RecipeEntity(
                    title = "Yogurt natural con frutos rojos",
                    ingredients = "1 taza de yogurt natural\n1/2 taza de frutos rojos\n2 cdas de granola baja en azúcar",
                    preparation = "1. Mezclar el yogurt con los frutos rojos.\n2. Espolvorear la granola por encima y servir.",
                    calories = 280,
                    category = "desayuno"
                ),

                // Comidas
                RecipeEntity(
                    title = "Pollo a la plancha con vegetales",
                    ingredients = "150 g de pechuga de pollo\nBrócoli, zanahoria, calabaza\n1 cdita de aceite de oliva\nSal y pimienta",
                    preparation = "1. Sazonar y cocinar la pechuga a la plancha.\n2. Cocer al vapor los vegetales.\n3. Servir acompañado del pollo.",
                    calories = 450,
                    category = "comida"
                ),
                RecipeEntity(
                    title = "Filete de salmón con arroz integral",
                    ingredients = "150 g de salmón\n1/2 taza de arroz integral\nEspárragos\nLimón, sal y pimienta",
                    preparation = "1. Hornear el salmón con limón y pimienta.\n2. Cocinar el arroz integral.\n3. Servir con espárragos al vapor.",
                    calories = 520,
                    category = "comida"
                ),
                RecipeEntity(
                    title = "Ensalada mexicana proteica",
                    ingredients = "Lechuga\nJitomate\nFrijoles negros\nQueso panela\n1/2 aguacate\nAderezo ligero",
                    preparation = "1. Mezclar todos los ingredientes.\n2. Agregar aderezo ligero al gusto y servir.",
                    calories = 380,
                    category = "comida"
                ),

                // Cenas
                RecipeEntity(
                    title = "Tostadas de atún light",
                    ingredients = "Atún en agua\nVerduras picadas\n2 tostadas horneadas\n1/4 aguacate",
                    preparation = "1. Mezclar atún con las verduras.\n2. Servir sobre las tostadas y añadir aguacate.",
                    calories = 300,
                    category = "cena"
                ),
                RecipeEntity(
                    title = "Ensalada de pollo",
                    ingredients = "Lechuga\nPollo desmenuzado\nPepino\nLimón y aderezo ligero",
                    preparation = "1. Mezclar los ingredientes.\n2. Añadir aderezo ligero y servir frío.",
                    calories = 340,
                    category = "cena"
                ),
                RecipeEntity(
                    title = "Rollitos de lechuga con carne magra",
                    ingredients = "150 g de carne molida magra\nVerduras salteadas\nHojas de lechuga grandes",
                    preparation = "1. Saltear la carne y las verduras.\n2. Colocar la mezcla sobre las hojas de lechuga y enrollar.",
                    calories = 280,
                    category = "cena"
                )
            )

            // Insertar toda la lista (REPLACE strategy)
            dao.insertAll(seed)
        }
    }
}