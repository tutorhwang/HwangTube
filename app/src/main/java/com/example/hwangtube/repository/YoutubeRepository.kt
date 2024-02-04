package com.example.hwangtube.repository

import com.example.hwangtube.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YoutubeRepository {
    suspend fun getTrendingVideos(region: String) = withContext(Dispatchers.IO) {
        RetrofitInstance.api.getTrendingVideos(regionCode = region).items
    }
}