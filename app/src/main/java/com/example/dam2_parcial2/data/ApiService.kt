package com.example.dam2_parcial2.data

import com.example.dam2_parcial2.model.QueryResponse
import com.example.dam2_parcial2.model.RecipeDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") ingredient: String,
        @Query("apiKey") apiKey: String
    ): QueryResponse

    @GET("recipes/{id}/nutritionWidget.json")
    fun getRecipeDetails(
        @Path("id")
        recipeId: Int,
        @Query("apiKey")
        apiKey: String,
    ): Call<RecipeDetail>
}