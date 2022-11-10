package com.myhand.nationalassembly.ui.view.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.myhand.nationalassembly.databinding.FragmentBudgetBinding
import com.myhand.nationalassembly.ui.view.report.adapter.ReportPagingAdapter
import com.myhand.nationalassembly.ui.view.report.viewmodel.ReportBudgetViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BudgetFragment : Fragment() {
    private var _binding: FragmentBudgetBinding? = null
    private val binding get() = _binding!!

    private val vm by viewModels<ReportBudgetViewModel>()
    private lateinit var budgetAdapter: ReportPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBudgetBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = vm

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpRecyclerView() {
        budgetAdapter = ReportPagingAdapter()

        binding.rvBudget.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = budgetAdapter
        }

        budgetAdapter.setOnItemClickListener {
            val action =
                ReportBudgetFragmentDirections.actionReportBudgetFragmentToWebViewFragment(
                    it.link ?: ""
                )
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}