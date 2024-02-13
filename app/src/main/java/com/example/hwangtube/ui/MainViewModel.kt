package com.example.hwangtube.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hwangtube.data.model.youtube.ListItem

class MainViewModel : ViewModel() {
    private val _favoriteList : MutableLiveData<List<ListItem.VideoItem>> = MutableLiveData()
    val favoriteList : LiveData<List<ListItem.VideoItem>> = _favoriteList
    fun addFavoriteItem(video: ListItem.VideoItem) {
        val currentList = _favoriteList.value?.toMutableList() ?: mutableListOf()
        currentList.add(0, video)
        _favoriteList.value = currentList
    }

    fun removeFavoriteItem(video: ListItem.VideoItem) {
        val currentList = _favoriteList.value?.toMutableList() ?: mutableListOf()
        currentList.remove(video)
        _favoriteList.value = currentList
    }
}