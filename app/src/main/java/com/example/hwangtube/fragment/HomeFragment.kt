package com.example.hwangtube.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.hwangtube.HomeViewModel
import com.example.hwangtube.activity.DetailActivity
import com.example.hwangtube.activity.EXTRA_VIDEO
import com.example.hwangtube.adapter.VideoListAdapter
import com.example.hwangtube.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy {
        VideoListAdapter { video ->
            val intent = Intent(activity, DetailActivity::class.java).apply {
                putExtra(EXTRA_VIDEO, video)
            }
            startActivity(intent)
        }
    }
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.adapter = adapter

            chipKorea.setOnClickListener { viewModel.fetchTrendingVideos("KR") }
            chipUs.setOnClickListener { viewModel.fetchTrendingVideos("US") }

            viewModel.fetchTrendingVideos()
            viewModel.trendingVideos.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}