package com.example.hwangtube.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Video(
    val channelTitle: String,
    val title: String,
    val thumbnail: String,
    val description: String
): Parcelable