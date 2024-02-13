package com.example.hwangtube.data.repository

import com.example.hwangtube.data.api.RetrofitInstance
import com.example.hwangtube.data.model.ai.AiRequest
import com.example.hwangtube.data.model.ai.AiResponse
import com.example.hwangtube.data.model.ai.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AiRepository {
    suspend fun createChatCompletion(message: String): AiResponse = withContext(Dispatchers.IO) {
        val request = AiRequest(messages = listOf(Message("user", message)))
        RetrofitInstance.aiApi.createChatCompletion(request)
    }
}