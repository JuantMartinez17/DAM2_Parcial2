package com.example.dam2_parcial2.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dam2_parcial2.adapter.AppDatabase
import com.example.dam2_parcial2.data.FavoriteRecipeDao

class FavoriteRecipesViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteRecipesViewModel::class.java)) {
            val favoriteRecipeDao: FavoriteRecipeDao =
                AppDatabase.getInstance(application).favoriteRecipeDao()
            return FavoriteRecipesViewModel(favoriteRecipeDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
