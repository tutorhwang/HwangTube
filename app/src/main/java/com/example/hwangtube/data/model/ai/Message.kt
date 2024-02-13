package com.example.hwangtube.data.model.ai


import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("role")
    val role: String,
    @SerializedName("content")
    val content: String
)