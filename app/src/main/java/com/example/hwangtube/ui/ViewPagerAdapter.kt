package com.example.hwangtube.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hwangtube.ui.home.HomeFragment
import com.example.hwangtube.ui.favorite.FavoriteFragment
import com.example.hwangtube.ui.recommend.RecommendFragment

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 3
    override fun createFragment(position: Int) = when (position) {
        0 -> HomeFragment()
        1-> RecommendFragment()
        2 -> FavoriteFragment()
        else -> throw IllegalStateException("Invalid position: $position")
    }
}
