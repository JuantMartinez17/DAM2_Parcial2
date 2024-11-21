package com.example.dam2_parcial2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dam2_parcial2.model.FavoriteRecipe
import kotlinx.coroutines.*
import com.example.dam2_parcial2.data.FavoriteRecipeDao

class FavoriteRecipesViewModel(private val favoriteRecipeDao: FavoriteRecipeDao) : AndroidViewModel(
    Application()
) {

    private val _favoriteRecipes = MutableLiveData<List<FavoriteRecipe>>()
    val favoriteRecipes: LiveData<List<FavoriteRecipe>> get() = _favoriteRecipes

    init {
        loadFavoriteRecipes()
    }

    private fun loadFavoriteRecipes() {
        viewModelScope.launch {
            _favoriteRecipes.value = favoriteRecipeDao.getAllFavoriteRecipes()
        }
    }
}
