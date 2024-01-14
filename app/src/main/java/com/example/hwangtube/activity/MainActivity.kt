package com.example.hwangtube.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hwangtube.R
import com.example.hwangtube.adapter.ViewPagerAdapter
import com.example.hwangtube.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val tabTitles =
        listOf(R.string.title_home, R.string.title_search, R.string.title_settings)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            viewPager.adapter = ViewPagerAdapter(this@MainActivity)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = getString(tabTitles[position])
            }.attach()
        }
    }

}
