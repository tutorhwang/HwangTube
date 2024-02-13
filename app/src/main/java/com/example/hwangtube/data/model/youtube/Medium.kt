package com.example.hwangtube.data.model.youtube


import com.google.gson.annotations.SerializedName

data class Medium(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)