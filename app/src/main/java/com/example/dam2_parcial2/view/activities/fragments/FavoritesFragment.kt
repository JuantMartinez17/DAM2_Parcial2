package com.example.dam2_parcial2.view.activities.fragments

import FavoriteRecipesAdapter
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dam2_parcial2.R
import com.example.dam2_parcial2.databinding.FragmentFavoritesBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dam2_parcial2.view.activities.RecipeDetailActivity
import com.example.dam2_parcial2.viewmodel.FavoriteRecipesViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var favoriteRecipesAdapter: FavoriteRecipesAdapter
    private val viewModel: FavoriteRecipesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoritesBinding.bind(view)

        setupRecyclerView()

        viewModel.favoriteRecipes.observe(viewLifecycleOwner, Observer { recipes ->
            favoriteRecipesAdapter.updateData(recipes)
        })
    }

    private fun setupRecyclerView() {
        favoriteRecipesAdapter = FavoriteRecipesAdapter { recipe ->
            val intent = Intent(requireContext(), RecipeDetailActivity::class.java).apply {
                putExtra("RECIPE_ID", recipe.recipeId)
            }
            startActivity(intent)
        }

        binding.rvFavorites.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavorites.adapter = favoriteRecipesAdapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoritesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoritesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}