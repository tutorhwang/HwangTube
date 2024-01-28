package com.example.hwangtube.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.hwangtube.activity.DetailActivity
import com.example.hwangtube.activity.EXTRA_VIDEO
import com.example.hwangtube.adapter.VideoListAdapter
import com.example.hwangtube.api.RetrofitInstance
import com.example.hwangtube.data.Item
import com.example.hwangtube.data.ListItem
import com.example.hwangtube.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
    private val _trendingVideos = MutableLiveData<List<Item>>()
    private val trendingVideos: LiveData<List<ListItem.VideoItem>> = _trendingVideos.switchMap {
        MutableLiveData(it.toVideoItem())
    }

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

            chipKorea.setOnClickListener { fetchTrendingVideos("KR") }
            chipUs.setOnClickListener { fetchTrendingVideos("US") }

            fetchTrendingVideos()
            trendingVideos.observe(viewLifecycleOwner) {
                adapter.submitList(it.toList())
            }
        }

    }

    private fun fetchTrendingVideos(region: String = "US") {
        CoroutineScope(Dispatchers.Main).launch {
            runCatching {
                val videos = getTrendingVideos(region)
                _trendingVideos.value = videos
            }.onFailure {
                Log.e("HwangTube", "fetchTrendingVideos() failed! : ${it.message}")
            }
        }
    }

    private suspend fun getTrendingVideos(region: String) = withContext(Dispatchers.IO) {
        RetrofitInstance.api.getTrendingVideos(regionCode = region).items
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

private fun List<Item>.toVideoItem(): List<ListItem.VideoItem> {
    return this.map {
        ListItem.VideoItem(
            channelTitle = it.snippet.channelTitle,
            title = it.snippet.title,
            thumbnail = it.snippet.thumbnails.high.url,
            description = it.snippet.description
        )
    }
}