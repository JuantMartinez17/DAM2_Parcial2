package com.example.dam2_parcial2.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dam2_parcial2.R
import com.example.dam2_parcial2.databinding.ItemViewBinding
import com.example.dam2_parcial2.model.Query
import com.squareup.picasso.Picasso

class QueryViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemViewBinding.bind(view)

    fun bind(query: Query){
        //set recipe title
        binding.tvItemTitle.text = query.title
        //load recipe image using picasso
        val imageUrl = query.image
        Picasso.with(binding.ivItemImage.context)
            .load(imageUrl)
            //.placeholder(R.drawable.placeholder)
            //.error(R.drawable.error_image)
            .into(binding.ivItemImage)
    }
}