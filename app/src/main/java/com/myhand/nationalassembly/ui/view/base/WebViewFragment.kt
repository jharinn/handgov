package com.myhand.nationalassembly.ui.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myhand.nationalassembly.databinding.FragmentWebviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : Fragment() {
    private var _binding: FragmentWebviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWebviewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}