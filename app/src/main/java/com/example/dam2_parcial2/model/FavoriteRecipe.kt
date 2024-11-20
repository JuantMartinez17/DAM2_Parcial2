package com.example.dam2_parcial2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteRecipe(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val recipeId: Int,
    val title: String,
    val imageUrl: String,
)
