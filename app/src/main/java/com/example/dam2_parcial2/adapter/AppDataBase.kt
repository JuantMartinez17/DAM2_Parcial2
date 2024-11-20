package com.example.dam2_parcial2.adapter

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dam2_parcial2.FavoriteRecipeDao
import com.example.dam2_parcial2.model.FavoriteRecipe

@Database(entities = [FavoriteRecipe::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun favoriteRecipeDao():FavoriteRecipeDao
    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: android.content.Context): AppDataBase {
            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "recipe_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}