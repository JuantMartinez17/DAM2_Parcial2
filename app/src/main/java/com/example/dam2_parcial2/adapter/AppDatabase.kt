package com.example.dam2_parcial2.adapter

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dam2_parcial2.data.FavoriteRecipeDao
import com.example.dam2_parcial2.model.FavoriteRecipe

@Database(entities = [FavoriteRecipe::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favoriteRecipeDao(): FavoriteRecipeDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: android.content.Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "recipe_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}