package com.example.hwangtube.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.hwangtube.ui.detail.DetailActivity
import com.example.hwangtube.ui.detail.EXTRA_VIDEO
import com.example.hwangtube.databinding.FragmentHomeBinding
import com.example.hwangtube.model.ListItem
import com.example.hwangtube.ui.MainViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy {
        VideoListAdapter(onClick = fun(video: ListItem) {
            val intent = Intent(activity, DetailActivity::class.java).apply {
                putExtra(EXTRA_VIDEO, video)
            }
            startActivity(intent)
        }, onFavoriteClick = fun(video: ListItem.VideoItem) {
            if(video.isFavorite) {
                sharedViewModel.addFavoriteItem(video)
            } else  {
                sharedViewModel.removeFavoriteItem(video)
            }
        })
    }
    private val viewModel by viewModels<HomeViewModel>()
    private val sharedViewModel by activityViewModels<MainViewModel>()

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