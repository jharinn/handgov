package com.myhand.nationalassembly.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.myhand.nationalassembly.R
import com.myhand.nationalassembly.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setBtnClickListener()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setBtnClickListener() {
        binding.btnMember.setOnClickListener(this)
        binding.btnBill.setOnClickListener(this)
        binding.btnReport.setOnClickListener(this)
        binding.btnSchedule.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_member -> {
                val action = MainFragmentDirections.actionMainFragmentToMemberSearchFragment()
                findNavController().navigate(action)
            }
            R.id.btn_schedule -> {
                val action = MainFragmentDirections.actionMainFragmentToScheduleFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}