package com.example.dam2_parcial2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dam2_parcial2.adapter.AppDatabase
import com.example.dam2_parcial2.adapter.FavoriteRecipeAdapter
import com.example.dam2_parcial2.databinding.FragmentFavoritesBinding
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var binding: FragmentFavoritesBinding
private lateinit var adapter: FavoriteRecipeAdapter
class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: FavoriteRecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewFavorites.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            val database = AppDatabase.getInstance(requireContext())
            val recipes = database.favoriteRecipeDao().getAll()

            adapter = FavoriteRecipeAdapter(recipes) { recipe ->
                val intent = Intent(requireContext(), RecipeDetailActivity::class.java)
                intent.putExtra("RECIPE_ID", recipe.recipeId)
                startActivity(intent)
            }

            binding.recyclerViewFavorites.adapter = adapter
        }
    }
}