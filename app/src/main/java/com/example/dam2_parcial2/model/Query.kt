package com.example.dam2_parcial2.model

import com.google.gson.annotations.SerializedName

data class Query(
    @SerializedName("id")
    val id:Int,
    @SerializedName("title")
    val title:String,
    @SerializedName("image")
    val image:String,
    @SerializedName("imageType")
    val imageType:String,
)
