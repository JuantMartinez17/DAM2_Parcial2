package com.example.dam2_parcial2.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dam2_parcial2.databinding.ItemFavoriteRecipeBinding
import com.example.dam2_parcial2.model.FavoriteRecipe
import com.squareup.picasso.Picasso

class FavoriteRecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemFavoriteRecipeBinding.bind(view)

    fun bind(recipe: FavoriteRecipe) {

        /*binding.tvItemTitle.text = recipe.title
        Picasso.with(binding.ivItemImage.context)
            .load(recipe.imageUrl)
            //.placeholder(R.drawable.placeholder)
            //.error(R.drawable.error_image)
            .into(binding.ivItemImage)*/

    }
}
