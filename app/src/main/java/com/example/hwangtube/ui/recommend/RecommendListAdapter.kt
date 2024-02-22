package com.example.hwangtube.ui.recommend

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hwangtube.data.model.youtube.ListItem
import com.example.hwangtube.databinding.FavoriteItemLayoutBinding
class RecommendListAdapter() :
    ListAdapter<ListItem.VideoItem, RecommendListAdapter.VideoItemHolder>(object :
        DiffUtil.ItemCallback<ListItem.VideoItem>() {
        override fun areItemsTheSame(oldItem: ListItem.VideoItem, newItem: ListItem.VideoItem): Boolean {
            return oldItem.thumbnail == newItem.thumbnail
        }

        override fun areContentsTheSame(oldItem: ListItem.VideoItem, newItem: ListItem.VideoItem): Boolean {
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
        private val titleView = binding.title
        private val thumbnailView = binding.mainImage

        fun bind(video: ListItem.VideoItem) {
            with(video) {
                Glide.with(itemView).load(thumbnail).into(thumbnailView)
                titleView.text = title
                channelNameView.text = channelTitle
            }
        }
    }
}
