package com.example.dam2_parcial2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dam2_parcial2.R
import com.example.dam2_parcial2.model.Query

class QueryAdapter (var results: List<Query>, private var onItemClick:(Query)->Unit): RecyclerView.Adapter<QueryViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return QueryViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: QueryViewHolder, position: Int) {
        val item = results[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    fun updateResults(newResults: List<Query>) {
        results = newResults
        notifyDataSetChanged()
    }
}