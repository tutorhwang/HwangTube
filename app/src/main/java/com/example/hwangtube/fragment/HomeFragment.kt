package com.example.hwangtube.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hwangtube.activity.DetailActivity
import com.example.hwangtube.activity.EXTRA_VIDEO
import com.example.hwangtube.adapter.VideoListAdapter
import com.example.hwangtube.data.Video
import com.example.hwangtube.data.VideoList
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
            recyclerView.adapter = adapter.apply { submitList(VideoList.list.toList()) }
            chipInsert.setOnClickListener {
                VideoList.list.add(
                    0,
                    Video(
                        "Bùm", //channel title
                        "Phân Tích Bí Ẩn Skibidi Toilet 69 Tập Full", // title
                        "https://i.ytimg.com/vi/dyuFvdd1Le4/mqdefault.jpg", //thumbnails.medium
                        "Phân Tích Bí Ẩn Skibidi Toilet 69 Tập Full --- Shopacc: https://bumroblox.net/k1 Kênh Phụ: ..." //description
                    )
                )
                adapter.submitList(VideoList.list.toList()) { recyclerView.scrollToPosition(0) }
            }

            chipDelete.setOnClickListener {
                if (VideoList.list.isNotEmpty()) {
                    VideoList.list.removeAt(0)
                    adapter.submitList(VideoList.list.toList()) { recyclerView.scrollToPosition(0) }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
