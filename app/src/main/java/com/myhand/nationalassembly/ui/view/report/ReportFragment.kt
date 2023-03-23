package com.myhand.nationalassembly.ui.view.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
        binding.viewmodel = vm

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView()
        fetchData()

        super.onViewCreated(view, savedInstanceState)
    }

    fun fetchData() {
        vm.fetchIssuePaging()
        vm.fetchPolicyPaging()
        vm.fetchGlobalReport(100, 1)
        vm.fetchLibraryReport(100, 1)


        //NARS 이슈
        collectLatestStateFlow(vm.fetchIssueReport) {
            issuePagingAdapter.submitData(it)
        }
        //NARS 정책연구
        collectLatestStateFlow(vm._fetchPolicyReport) {
            policyPagingAdapter.submitData(it)
        }
        //NARS 글로벌
        vm.fetchGlobalResult.observe(viewLifecycleOwner) { result ->
            globalAdapter.submitList(result)
        }
        //국회도서관
        vm.fetchLibraryReport.observe(viewLifecycleOwner) { result ->
            libraryAdapter.submitList(result)
        }
    }

    private fun setUpRecyclerView() {
        issuePagingAdapter = ReportPagingAdapter()
        policyPagingAdapter = ReportPagingAdapter()
        globalAdapter = ReportAdapter()
        libraryAdapter = ReportAdapter()


        // nars issue
        binding.rvNarsIssue.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = issuePagingAdapter
        }
        issuePagingAdapter.setOnItemClickListener {
            moveToLink(it)
        }

        //nars policy
        policyPagingAdapter.setOnItemClickListener {
            moveToLink(it)
        }
        binding.rvNarsResearch.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = policyPagingAdapter
        }

        //nars global
        globalAdapter.setOnItemClickListener {
            moveToLink(it)
        }
        binding.rvNarsGlobal.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = globalAdapter
        }


        //국회도서관
        libraryAdapter.setOnItemClickListener {
            moveToLink(it)
        }

        binding.rvLibraryResearch.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = libraryAdapter
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