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

    private var recipeDetail: RecipeDetail? = null // Para almacenar los detalles cargados

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

        // Agregar a favoritos al hacer clic en el botón
        binding.btnAddToFavorites.setOnClickListener {
            recipeId?.let { id ->
                recipeTitle?.let { title ->
                    recipeImage?.let { image ->
                        // Llamamos a la función suspendida en una coroutine
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

    // Función para agregar una receta a favoritos utilizando los datos correctos
    private suspend fun addToFavorites(recipeId: Int, title: String, image: String, imageType: String) {
        val favoriteRecipe = FavoriteRecipe(
            recipeId = recipeId,
            title = title,
            image = image,
            imageType = imageType
        )

        // Insertar en la base de datos en un hilo IO
        withContext(Dispatchers.IO) {
            favoriteRecipeDao.insertFavoriteRecipe(favoriteRecipe)
        }

        // Mostrar mensaje en el hilo principal
        withContext(Dispatchers.Main) {
            Toast.makeText(this@RecipeDetailActivity, "Receta agregada a favoritos", Toast.LENGTH_SHORT).show()
        }
    }
}