package com.example.dam2_parcial2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dam2_parcial2.model.FavoriteRecipe
@Dao
interface FavoriteRecipeDao {

    @Insert
    suspend fun insertFavoriteRecipe(recipe: FavoriteRecipe)

    @Query("SELECT * FROM favorite_recipes WHERE recipeId = :recipeId LIMIT 1")
    suspend fun getFavoriteRecipeById(recipeId: Int): FavoriteRecipe?

    @Query("SELECT * FROM favorite_recipes")
    suspend fun getAllFavoriteRecipes(): List<FavoriteRecipe>
}