package com.example.dam2_parcial2.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dam2_parcial2.databinding.ItemFavoriteRecipeBinding
import com.example.dam2_parcial2.model.FavoriteRecipe
import com.squareup.picasso.Picasso

class FavoriteRecipeViewHolder(
    private val binding: ItemFavoriteRecipeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(recipe: FavoriteRecipe, onItemClick: (FavoriteRecipe) -> Unit) {
        binding.apply {
            binding.tvRecipeTitle.text = recipe.title
            Picasso.with(binding.ivRecipeImage.context)
                .load(recipe.image)
                .into(ivRecipeImage)
            root.setOnClickListener { onItemClick(recipe) }
        }
    }
}