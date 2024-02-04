package com.example.hwangtube.network

import com.example.hwangtube.model.VideoModel
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_MAX_RESULT = 20
private const val API_REGION = "US"

private const val youtubeApiKey = "AIzaSyCwSh2ytvLEZxrFRAzR3mna1aeGJqmpbhg"
interface YoutubeAPI {
    @GET("videos")
    suspend fun getTrendingVideos(
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular",
        @Query("maxResults") maxResults: Int = API_MAX_RESULT,
        @Query("regionCode") regionCode: String = API_REGION,
        @Query("key") apiKey: String = youtubeApiKey
    ): VideoModel
}