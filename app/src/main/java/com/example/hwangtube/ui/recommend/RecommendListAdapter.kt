package com.example.hwangtube.ui.recommend

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hwangtube.databinding.FavoriteItemLayoutBinding

class RecommendListAdapter() :
    ListAdapter<String, RecommendListAdapter.VideoItemHolder>(object :
        DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemHolder {
        val binding =
            FavoriteItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoItemHolder(binding)
    }

    override fun onBindViewHolder(itemHolder: VideoItemHolder, position: Int) {
        runCatching {
            itemHolder.bind(getItem(position))
        }.onFailure { exception ->
            Log.e("FavoriteListAdapter", "Exception! ${exception.message}")
        }
    }

    inner class VideoItemHolder(binding: FavoriteItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val channelNameView = binding.channelName

        fun bind(video: String) {
            channelNameView.text = video
        }
    }
}
