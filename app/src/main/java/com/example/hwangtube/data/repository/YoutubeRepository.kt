package com.example.hwangtube.data.repository

import com.example.hwangtube.data.api.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YoutubeRepository {
    suspend fun getTrendingVideos(region: String) = withContext(Dispatchers.IO) {
        RetrofitInstance.youtubeApi.getTrendingVideos(regionCode = region).items
    }

    suspend fun searchChannels(channelName: String) = withContext(Dispatchers.IO) {
        RetrofitInstance.youtubeApi.searchChannels(query = channelName)
    }
}