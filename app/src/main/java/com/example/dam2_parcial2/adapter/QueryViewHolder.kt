package com.example.dam2_parcial2.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dam2_parcial2.databinding.ItemViewBinding
import com.example.dam2_parcial2.model.Query
import com.squareup.picasso.Picasso

class QueryViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemViewBinding.bind(view)

    fun bind(query: Query){
        binding.tvItemTitle.text = query.title
        val imageUrl = query.image
        Picasso.with(binding.ivItemImage.context)
            .load(imageUrl)
            .into(binding.ivItemImage)
    }
}