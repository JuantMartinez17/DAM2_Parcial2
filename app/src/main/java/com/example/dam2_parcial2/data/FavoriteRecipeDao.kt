package com.example.dam2_parcial2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dam2_parcial2.model.FavoriteRecipe
@Dao
interface FavoriteRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteRecipe(favoriteRecipe: FavoriteRecipe)

    @Query("SELECT * FROM favorite_recipes")
    fun getAllFavoriteRecipes(): List<FavoriteRecipe>

    @Delete
    fun deleteFavoriteRecipe(favoriteRecipe: FavoriteRecipe)
}