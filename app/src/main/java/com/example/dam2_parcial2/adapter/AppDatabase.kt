package com.example.dam2_parcial2.adapter

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dam2_parcial2.data.FavoriteRecipeDao
import com.example.dam2_parcial2.model.FavoriteRecipe

@Database(entities = [FavoriteRecipe::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteRecipeDao(): FavoriteRecipeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "favorite_recipes_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }

        // Cerrar la base de datos cuando ya no se necesite
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}