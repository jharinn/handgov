package com.myhand.nationalassembly.ui.view.member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myhand.nationalassembly.databinding.FragmentMemberBillBinding
import com.myhand.nationalassembly.ui.view.member.adapter.MemberInfoItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemberBillFragment(infoItem: MemberInfoItem) : Fragment() {

    private var _binding: FragmentMemberBillBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMemberBillBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}