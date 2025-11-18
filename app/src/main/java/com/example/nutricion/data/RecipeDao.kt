package com.example.nutricion.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes")
    fun getAll(): LiveData<List<RecipeEntity>>

    @Query("SELECT * FROM recipes WHERE category = :category ORDER BY id")
    fun getByCategory(category: String): LiveData<List<RecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<RecipeEntity>)

    @Update
    suspend fun update(recipe: RecipeEntity)

    @Delete
    suspend fun delete(recipe: RecipeEntity)

    // Versión suspend para leer lista en corrutina
    @Query("SELECT * FROM recipes WHERE category = :category ORDER BY id")
    suspend fun getByCategoryList(category: String): List<RecipeEntity>

    // Versión sincronizada (sin LiveData)
    @Query("SELECT * FROM recipes WHERE category = :category ORDER BY id")
    suspend fun getByCategoryOnce(category: String): List<RecipeEntity>
}
