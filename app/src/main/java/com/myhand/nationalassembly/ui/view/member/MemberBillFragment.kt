package com.myhand.nationalassembly.ui.view.member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.myhand.nationalassembly.databinding.FragmentMemberBillBinding
import com.myhand.nationalassembly.ui.view.member.adapter.MemberBillAdapter
import com.myhand.nationalassembly.ui.view.member.adapter.MemberInfoItem
import com.myhand.nationalassembly.ui.view.member.viewmodel.MemberViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemberBillFragment(val infoItem: MemberInfoItem) : Fragment() {

    private var _binding: FragmentMemberBillBinding? = null
    private val binding get() = _binding!!
    private val vm by viewModels<MemberViewModel>()
    private lateinit var memberBillAdapter: MemberBillAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMemberBillBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView()
        fetchMemberBill()

        vm.fetchMemberBillResult.observe(viewLifecycleOwner) { result ->
            memberBillAdapter.submitList(result)

            if (result.isEmpty())
                binding.tvNoData.visibility = View.VISIBLE
            else
                binding.tvNoData.visibility = View.GONE
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpRecyclerView() {
        memberBillAdapter = MemberBillAdapter()

        binding.rvSchedule.apply {
            adapter = memberBillAdapter
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
//        memberBillAdapter.setOnItemClickListener {
//            val billId = it.billId.toString()
//            val action =
//                MemberDetailGraphDirections.actionGlobalBillSearchFragment(billId)
//            findNavController().navigate(action)
//        }
    }

    fun fetchMemberBill() {
        vm.fetchMemberBill(
            infoItem.name ?: "",
            infoItem.hjName ?: ""
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}