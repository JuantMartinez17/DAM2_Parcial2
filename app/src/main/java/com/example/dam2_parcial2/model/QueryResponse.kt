package com.example.dam2_parcial2.model

import com.google.gson.annotations.SerializedName

data class QueryResponse(
    @SerializedName("results")
    val results: List<Query>
)
