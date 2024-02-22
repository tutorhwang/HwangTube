package com.example.hwangtube.data.model.youtube.search

import com.example.hwangtube.data.model.youtube.ListItem
import com.example.hwangtube.data.model.youtube.Thumbnails

data class SearchResponse(
    val items: List<SearchItem>,
    val nextPageToken: String?
)

data class SearchItem(
    val id: SearchResultId,
    val snippet: SearchResultSnippet
)

data class SearchResultId(
    val kind: String,
    val videoId: String? = null,
    val channelId: String? = null,
    val playlistId: String? = null
)

data class SearchResultSnippet(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: Thumbnails,
    val channelTitle: String,
)

fun List<SearchItem>.toVideoItem(): List<ListItem.VideoItem> {
    return this.map {
        ListItem.VideoItem(
            channelTitle = it.snippet.channelTitle,
            title = it.snippet.title,
            thumbnail = it.snippet.thumbnails.high.url,
            description = it.snippet.description
        )
    }
}