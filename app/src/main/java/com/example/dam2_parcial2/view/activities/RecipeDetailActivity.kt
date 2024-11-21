package com.example.dam2_parcial2.view.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.dam2_parcial2.BuildConfig
import com.example.dam2_parcial2.data.FavoriteRecipeDao
import com.example.dam2_parcial2.data.RetrofitClient
import com.example.dam2_parcial2.adapter.AppDatabase
import com.example.dam2_parcial2.databinding.ActivityRecipeDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.dam2_parcial2.model.RecipeDetail

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailBinding
    private lateinit var database: AppDatabase
    private lateinit var favoriteRecipeDao: FavoriteRecipeDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getInstance(this)
        favoriteRecipeDao = database.favoriteRecipeDao()

       // val recipeDetail = intent.getParcelableExtra<RecipeDetail>("RECIPE_DETAIL")

        val apiKey = BuildConfig.MY_API_KEY
        val recipeId = intent.getIntExtra("RECIPE_ID", -1)
        if (recipeId != -1) {
            getRecipeDetails(recipeId, apiKey)
        }
    }

    private fun getRecipeDetails(recipeId: Int, apiKey: String) {
        val apiService = RetrofitClient.apiService
        apiService.getRecipeDetails(recipeId, apiKey).enqueue(object : Callback<RecipeDetail> {
            override fun onResponse(call: Call<RecipeDetail>, response: Response<RecipeDetail>) {
                if (response.isSuccessful) {
                    val recipeDetail = response.body()
                    recipeDetail?.let {
                        binding.tvCalories.text = "Calories: ${it.calories}"
                        binding.tvCarbs.text = "Carbs: ${it.carbs}"
                        binding.tvFat.text = "Fat: ${it.fat}"
                        binding.tvProtein.text = "Protein: ${it.protein}"
                    }
                }
            }

            override fun onFailure(call: Call<RecipeDetail>, t: Throwable) {
                Log.e("ERROR: ", "Error al obtener el detalle de la receta")
            }
        })
    }
}