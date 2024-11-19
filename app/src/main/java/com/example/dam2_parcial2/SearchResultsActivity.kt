package com.example.dam2_parcial2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dam2_parcial2.adapter.QueryAdapter
import com.example.dam2_parcial2.databinding.ActivitySearchResultsBinding
import com.example.dam2_parcial2.model.QueryResponse
import com.example.dam2_parcial2.model.RecipeDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.launch

class SearchResultsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchResultsBinding
    private lateinit var queryAdapter: QueryAdapter
    private val apiService = RetrofitClient.apiService
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        queryAdapter = QueryAdapter(emptyList()) { query ->
            val intent = Intent(this, RecipeDetail::class.java)
            intent.putExtra("RECIPE_ID", query.id)
            startActivity(intent)
        }

        binding.rvResults.layoutManager = LinearLayoutManager(this)
        binding.rvResults.adapter = queryAdapter

        val apiKey = BuildConfig.MY_API_KEY
        val searchQuery = intent.getStringExtra("search_query")

        if (!searchQuery.isNullOrBlank()) {
            searchRecipes(searchQuery, apiKey)
        }

    }

    private fun searchRecipes(ingredient: String, apiKey: String) {
        coroutineScope.launch {
            try {
                val response = apiService.searchRecipes(ingredient, apiKey)
                Log.d("API Response", response.results.toString())
                updateUI(response)
            } catch (e: Exception) {
                Log.e("Error:", "${e.message}")
            }
        }
    }

    private suspend fun updateUI(response: QueryResponse) {
        withContext(Dispatchers.Main) {
            queryAdapter.updateResults(response.results)
        }
    }
}