package com.example.hwangtube.data.model

import com.google.gson.annotations.SerializedName

data class VideoModel(
    @SerializedName("etag")
    val etag: String,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("nextPageToken")
    val nextPageToken: String,
    @SerializedName("pageInfo")
    val pageInfo: PageInfo
)

fun List<Item>.toVideoItem(): List<ListItem.VideoItem> {
    return this.map {
        ListItem.VideoItem(
            channelTitle = it.snippet.channelTitle,
            title = it.snippet.title,
            thumbnail = it.snippet.thumbnails.high.url,
            description = it.snippet.description
        )
    }
}