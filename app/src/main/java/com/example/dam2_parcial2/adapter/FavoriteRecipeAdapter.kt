package com.example.dam2_parcial2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dam2_parcial2.R
import com.example.dam2_parcial2.model.FavoriteRecipe

class FavoriteRecipeAdapter(
    private val favoriteRecipes: List<FavoriteRecipe>,
    private val onItemClick: (FavoriteRecipe) -> Unit
) : RecyclerView.Adapter<FavoriteRecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteRecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_favorite_recipe, parent, false)
        return FavoriteRecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteRecipeViewHolder, position: Int) {
        val recipe = favoriteRecipes[position]
        holder.bind(recipe)
        holder.itemView.setOnClickListener {
            onItemClick(recipe)
        }
    }

    override fun getItemCount(): Int = favoriteRecipes.size
}