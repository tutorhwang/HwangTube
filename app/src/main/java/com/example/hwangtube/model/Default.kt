package com.example.hwangtube.model


import com.google.gson.annotations.SerializedName

data class Default(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)