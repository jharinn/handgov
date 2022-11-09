package com.myhand.nationalassembly.ui.view.member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myhand.nationalassembly.databinding.FragmentMemberInfoBinding
import com.myhand.nationalassembly.ui.view.member.adapter.MemberInfoItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemberInfoFragment(val infoItem: MemberInfoItem) : Fragment() {

    private var _binding: FragmentMemberInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMemberInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setInfoTable()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setInfoTable() {
        val birthDay = infoItem.bthDate + "(" + infoItem.bthName + ")"

        binding.apply {
            tvJobName.text = infoItem.jobName
            tvBthDate.text = birthDay
            tvUnit.text = infoItem.unit
            tvElectLocalName.text = infoItem.electLocalName
            tvOriLocalName.text = infoItem.oriLocalName
            tvSecretary.text = infoItem.secretary
            tvStaff.text = infoItem.staff
            tvAssemAddress.text = infoItem.assemAddress
            tvMemHistory.text = infoItem.memTitle
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}