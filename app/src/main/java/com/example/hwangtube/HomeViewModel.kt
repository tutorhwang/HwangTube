package com.example.hwangtube

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.hwangtube.data.Item
import com.example.hwangtube.data.ListItem
import com.example.hwangtube.data.toVideoItem
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

private val TAG = "HomeViewModel"

class HomeViewModel(val repository: YoutubeRepository = YoutubeRepository()) : ViewModel() {
    private val _trendingVideos = MutableLiveData<List<Item>>()
    val trendingVideos: LiveData<List<ListItem.VideoItem>> = _trendingVideos.switchMap {
        MutableLiveData(it.toVideoItem())
    }


    fun fetchTrendingVideos(region: String = "US") {
        viewModelScope.launch {
            runCatching {
                val videos = repository.getTrendingVideos(region)
                _trendingVideos.value = videos
            }.onFailure {
                Log.e(TAG, "fetchTrendingVideos() failed! : ${it.message}")
                handleException(it)
            }
        }
    }

    private fun handleException(e: Throwable) {
        when (e) {
            is HttpException -> {
                val errorJsonString = e.response()?.errorBody()?.string()
                Log.e(TAG, "HTTP error: $errorJsonString")
            }

            is IOException -> Log.e(TAG, "Network error: $e")
            else -> Log.e(TAG, "Unexpected error: $e")
        }
    }
}