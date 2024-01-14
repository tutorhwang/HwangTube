package com.example.hwangtube.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hwangtube.fragment.HomeFragment
import com.example.hwangtube.fragment.SearchFragment
import com.example.hwangtube.fragment.SettingFragment

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 3
    override fun createFragment(position: Int) = when (position) {
        0 -> HomeFragment()
        1 -> SearchFragment()
        2 -> SettingFragment()
        else -> throw IllegalStateException("Invalid position: $position")
    }
}
