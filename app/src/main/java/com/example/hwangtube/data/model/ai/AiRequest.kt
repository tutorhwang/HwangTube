package com.example.hwangtube.data.model.ai

import com.example.hwangtube.data.api.AI_MODEL

data class AiRequest(
    val model: String = AI_MODEL,
    val messages: List<Message>
)