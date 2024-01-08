package com.example.hwangtube.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.hwangtube.R
import com.example.hwangtube.databinding.ActivityMainBinding
import com.example.hwangtube.fragment.HomeFragment
import com.example.hwangtube.fragment.SearchFragment
import com.example.hwangtube.fragment.SettingFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    HomeFragment().loadFragment()
                    true
                }

                R.id.navigation_search -> {
                    SearchFragment().loadFragment()
                    true
                }

                R.id.navigation_settings -> {
                    SettingFragment().loadFragment()
                    true
                }

                else -> false
            }
        }

        // 기본으로 첫 번째 탭의 Fragment 표시
        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.navigation_home
        }
    }

    private fun Fragment.loadFragment() {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, this@loadFragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}
