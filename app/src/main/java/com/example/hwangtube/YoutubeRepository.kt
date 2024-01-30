package com.example.hwangtube

import com.example.hwangtube.api.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YoutubeRepository {
    suspend fun getTrendingVideos(region: String) = withContext(Dispatchers.IO) {
        RetrofitInstance.api.getTrendingVideos(regionCode = region).items
    }
}