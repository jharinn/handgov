package com.myhand.nationalassembly.ui.view.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.myhand.nationalassembly.databinding.FragmentReportBinding
import com.myhand.nationalassembly.ui.view.report.adapter.ReportAdapter
import com.myhand.nationalassembly.ui.view.report.adapter.ReportItem
import com.myhand.nationalassembly.ui.view.report.adapter.ReportPagingAdapter
import com.myhand.nationalassembly.ui.view.report.viewmodel.ReportBudgetViewModel
import com.myhand.nationalassembly.util.Const
import com.myhand.nationalassembly.util.collectLatestStateFlow
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReportFragment : Fragment() {
    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding!!

    private val vm by viewModels<ReportBudgetViewModel>()
    private lateinit var issuePagingAdapter: ReportPagingAdapter
    private lateinit var policyPagingAdapter: ReportPagingAdapter
    private lateinit var globalAdapter: ReportAdapter
    private lateinit var libraryAdapter: ReportAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        //binding.viewmodel = vm

        //NARS 이슈
        collectLatestStateFlow(vm.fetchIssueReport) {
            issuePagingAdapter.submitData(it)
        }
        //NARS 정책연구
        collectLatestStateFlow(vm.fetchPolicyReport) {
            policyPagingAdapter.submitData(it)
        }
        //NARS 글로벌
        vm.fetchGlobalReport.observe(viewLifecycleOwner) { result ->
            globalAdapter.submitList(result)
        }
        //국회도서관
        vm.fetchLibraryReport.observe(viewLifecycleOwner) { result ->
            libraryAdapter.submitList(result)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpRecyclerView() {
        issuePagingAdapter = ReportPagingAdapter()
        policyPagingAdapter = ReportPagingAdapter()
        globalAdapter = ReportAdapter()
        libraryAdapter = ReportAdapter()

        issuePagingAdapter.setOnItemClickListener {
            moveToLink(it)
        }

        policyPagingAdapter.setOnItemClickListener {
            moveToLink(it)
        }

        globalAdapter.setOnItemClickListener {
            moveToLink(it)
        }

        libraryAdapter.setOnItemClickListener {
            moveToLink(it)
        }
    }

    private fun moveToLink(item: ReportItem) {
        val link = item.link ?: Const.DEFAULT_URL
        val action =
            ReportBudgetFragmentDirections.actionReportBudgetFragmentToWebViewFragment(link)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}