package com.example.hwangtube.ui.home

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hwangtube.R
import com.example.hwangtube.data.model.ListItem
import com.example.hwangtube.databinding.ItemLayout2Binding
import com.example.hwangtube.databinding.ItemLayoutBinding

class VideoListAdapter(
    private val onClick: (ListItem) -> Unit,
    private val onFavoriteClick: (ListItem.VideoItem) -> Unit
) :
    ListAdapter<ListItem, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return when {
                oldItem is ListItem.HeaderItem && newItem is ListItem.HeaderItem ->
                    oldItem.thumbnail == newItem.thumbnail

                oldItem is ListItem.VideoItem && newItem is ListItem.VideoItem ->
                    oldItem.title == newItem.title

                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding =
                    ItemLayout2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderItemHolder(binding)
            }

            else -> {
                val binding =
                    ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                VideoItemHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(itemHolder: RecyclerView.ViewHolder, position: Int) {
        runCatching {
            when (val item = getItem(position)) {
                is ListItem.HeaderItem -> {
                    (itemHolder as HeaderItemHolder).bind(item)
                }

                is ListItem.VideoItem -> {
                    (itemHolder as VideoItemHolder).bind(item)
                }
            }
        }.onFailure { exception ->
            Log.e("VideoListAdapter", "Exception! ${exception.message}")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ListItem.HeaderItem -> TYPE_HEADER
            is ListItem.VideoItem -> TYPE_CONTENT
        }
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_CONTENT = 1
    }

    inner class VideoItemHolder(binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val channelLogoView = binding.logo
        private val channelNameView = binding.channelName
        private val titleView = binding.title
        private val thumbnailView = binding.mainImage
        private val favoriteButton = binding.ivHeart

        fun bind(video: ListItem.VideoItem) {
            with(video) {
                Glide.with(itemView).load(thumbnail).into(thumbnailView)
                thumbnailView.setOnClickListener { onClick(this) }
                titleView.text = title
                channelNameView.text = channelTitle
                channelLogoView.setImageResource(R.drawable.haelin)
                favoriteButton.setFavorite(isFavorite)
                favoriteButton.setOnClickListener {
                    isFavorite = !isFavorite
                    (it as ImageView).setFavorite(isFavorite)
                    it.startFavoriteAnimation(isFavorite)
                    onFavoriteClick(this)
                }
            }
        }

        private fun ImageView.setFavorite(isFavorite: Boolean) {
            val favoriteRes = if (isFavorite) R.color.favorite_on else R.color.favorite_off
            val favoriteColor = ContextCompat.getColor(context, favoriteRes)
            DrawableCompat.setTint(drawable, favoriteColor)
        }

        private fun ImageView.startFavoriteAnimation(favorite: Boolean) {
            val startColor = if (favorite) R.color.favorite_off else R.color.favorite_on
            val endColor = if (favorite) R.color.favorite_on else R.color.favorite_off
            val colorAnimation = ValueAnimator.ofObject(
                ArgbEvaluator(),
                ContextCompat.getColor(context, startColor),
                ContextCompat.getColor(context, endColor)
            )
            colorAnimation.duration = 500L
            colorAnimation.addUpdateListener { animator ->
                val animatedColor = animator.animatedValue as Int
                val wrappedDrawable = DrawableCompat.wrap(drawable).mutate()
                DrawableCompat.setTint(wrappedDrawable, animatedColor)
                setImageDrawable(wrappedDrawable)
            }
            colorAnimation.start()
        }
    }

    inner class HeaderItemHolder(binding: ItemLayout2Binding) :
        RecyclerView.ViewHolder(binding.root) {
        private val thumbnailView = binding.mainImage

        fun bind(item: ListItem.HeaderItem) {
            with(item) {
                Glide.with(itemView).load(thumbnail).into(thumbnailView)
            }
        }
    }
}
