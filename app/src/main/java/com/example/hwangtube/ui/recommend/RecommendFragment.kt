package com.example.hwangtube.ui.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.hwangtube.databinding.FragmentRecommendBinding
import com.example.hwangtube.ui.MainViewModel

class RecommendFragment : Fragment() {
    private var _binding: FragmentRecommendBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel by activityViewModels<MainViewModel>()
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
        binding.recyclerView.adapter = null

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}