package com.example.dam2_parcial2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dam2_parcial2.model.FavoriteRecipe
import androidx.lifecycle.ViewModel

class FavoriteRecipesViewModel : ViewModel() {

    private val _favoriteRecipes = MutableLiveData<List<FavoriteRecipe>>()
    val favoriteRecipes: LiveData<List<FavoriteRecipe>> get() = _favoriteRecipes

    init {
        loadFavoriteRecipes()
    }

    private fun loadFavoriteRecipes() {
        // Datos de prueba (puedes conectarlo a Room más adelante)
        val sampleRecipes = listOf(
            FavoriteRecipe(recipeId = 1, title = "Receta 1", image = "https://via.placeholder.com/150", imageType = "JPG"),
            FavoriteRecipe(recipeId = 2, title = "Receta 2", image = "https://via.placeholder.com/150", imageType = "JPG")
        )
        _favoriteRecipes.value = sampleRecipes
    }

    fun addRecipe(recipe: FavoriteRecipe) {
        val updatedList = _favoriteRecipes.value.orEmpty().toMutableList()
        updatedList.add(recipe)
        _favoriteRecipes.value = updatedList
    }

    fun removeRecipe(recipe: FavoriteRecipe) {
        val updatedList = _favoriteRecipes.value.orEmpty().toMutableList()
        updatedList.remove(recipe)
        _favoriteRecipes.value = updatedList
    }
}