package com.example.hwangtube.data.api

import com.example.hwangtube.data.model.ai.AiRequest
import com.example.hwangtube.data.model.ai.AiResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

private const val AI_API_KEY = "sk-......................fwlVvl4"
val AI_MODEL = "gpt-3.5-turbo"

interface AiApi {
    @Headers("Authorization: Bearer $AI_API_KEY")
    @POST("chat/completions")
    suspend fun createChatCompletion(@Body body: AiRequest): AiResponse
}