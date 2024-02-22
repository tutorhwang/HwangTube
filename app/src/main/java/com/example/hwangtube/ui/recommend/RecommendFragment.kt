package com.example.hwangtube.ui.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.hwangtube.databinding.FragmentRecommendBinding
import com.example.hwangtube.ui.MainViewModel

class RecommendFragment : Fragment() {
    private var _binding: FragmentRecommendBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel by activityViewModels<MainViewModel>()
    private val viewModel by viewModels<RecommendViewModel>()
    private val adapter by lazy { RecommendListAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        _binding = FragmentRecommendBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.adapter = adapter
            buttonRefresh.setOnClickListener {
                val recentFavorite = sharedViewModel.favoriteList.value?.take(5) ?: run {
                    Toast.makeText(
                        context,
                        "추가된 Favorite이 없습니다.",
                        Toast.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }
                viewModel.fetchRecommendChanelList(recentFavorite)
            }
            viewModel.recommendChannelList.observe(viewLifecycleOwner) {
                adapter.submitList(it) {
                    recyclerView.scrollToPosition(0)
                }
            }
            viewModel.recommendResponse.observe(viewLifecycleOwner) {
                tvResponse.text = it
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}