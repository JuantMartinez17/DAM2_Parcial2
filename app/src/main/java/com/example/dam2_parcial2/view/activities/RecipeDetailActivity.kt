package com.example.dam2_parcial2.view.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dam2_parcial2.BuildConfig
import com.example.dam2_parcial2.data.FavoriteRecipeDao
import com.example.dam2_parcial2.data.RetrofitClient
import com.example.dam2_parcial2.adapter.AppDatabase
import com.example.dam2_parcial2.databinding.ActivityRecipeDetailBinding
import com.example.dam2_parcial2.model.FavoriteRecipe
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.dam2_parcial2.model.RecipeDetail
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailBinding
    private lateinit var database: AppDatabase
    private lateinit var favoriteRecipeDao: FavoriteRecipeDao

    private var recipeDetail: RecipeDetail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getInstance(this)
        favoriteRecipeDao = database.favoriteRecipeDao()

        val recipeId = intent.getIntExtra("RECIPE_ID", -1)
        val recipeTitle = intent.getStringExtra("RECIPE_TITLE")
        val recipeImage = intent.getStringExtra("RECIPE_IMAGE")
        if (recipeId != -1 && recipeTitle != null && recipeImage != null) {
            val apiKey = BuildConfig.MY_API_KEY
            getRecipeDetails(recipeId, apiKey)
            binding.tvRecipeTitle.text = recipeTitle
            Picasso.with(this)
                .load(recipeImage)
                .into(binding.ivRecipeImage)
        }

        binding.btnAddToFavorites.setOnClickListener {
            recipeId?.let { id ->
                recipeTitle?.let { title ->
                    recipeImage?.let { image ->
                        CoroutineScope(Dispatchers.Main).launch {
                            addToFavorites(id, title, image, "image_type_here")
                        }
                    }
                }
            }
        }
    }

    private fun getRecipeDetails(recipeId: Int, apiKey: String) {
        val apiService = RetrofitClient.apiService
        apiService.getRecipeDetails(recipeId, apiKey).enqueue(object : Callback<RecipeDetail> {
            override fun onResponse(call: Call<RecipeDetail>, response: Response<RecipeDetail>) {
                if (response.isSuccessful) {
                    recipeDetail = response.body()
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

    private suspend fun addToFavorites(recipeId: Int, title: String, image: String, imageType: String) {
        val existingRecipe = favoriteRecipeDao.getFavoriteRecipeById(recipeId)

        if (existingRecipe != null) {

            Log.d("RecipeDetailActivity", "La receta ya está en favoritos: $title, ID: $recipeId")
            return
        }

        val favoriteRecipe = FavoriteRecipe(
            recipeId = recipeId,
            title = title,
            image = image,
            imageType = imageType
        )

        withContext(Dispatchers.IO) {
            favoriteRecipeDao.insertFavoriteRecipe(favoriteRecipe)
            Log.d("RecipeDetailActivity", "Receta agregada a favoritos: $title, ID: $recipeId")
        }

        val allFavorites = favoriteRecipeDao.getAllFavoriteRecipes()
        Log.d("RecipeDetailActivity", "Recetas en la base de datos: $allFavorites")

        withContext(Dispatchers.Main) {
            Toast.makeText(this@RecipeDetailActivity, "Receta agregada a favoritos", Toast.LENGTH_SHORT).show()
        }
    }
}