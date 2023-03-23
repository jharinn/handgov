package com.myhand.nationalassembly.ui.view.bill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.myhand.nationalassembly.R
import com.myhand.nationalassembly.databinding.FragmentBillSearchBinding
import com.myhand.nationalassembly.ui.view.bill.adapter.BillPagingAdapter
import com.myhand.nationalassembly.ui.view.bill.viewmodel.BillViewModel
import com.myhand.nationalassembly.util.Const
import com.myhand.nationalassembly.util.LogUtil
import com.myhand.nationalassembly.util.collectLatestStateFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BillSearchFragment : Fragment(), AdapterView.OnItemSelectedListener, View.OnClickListener {
    private var _binding: FragmentBillSearchBinding? = null
    private val binding get() = _binding!!

    private val vm by viewModels<BillViewModel>()
    private lateinit var billSearchAdapter: BillPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBillSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setBtnClickListener()
        setDefaultBill()
        setUpRecyclerView()
        setSpinner()
        collectLatestStateFlow(vm.fetchBillDataResult) {
            billSearchAdapter.submitData(it)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setDefaultBill() {
        vm.fetchBillPaging()
    }

    private fun searchBill() {
        val age = binding.spinnerAge.selectedItem.toString()
        val procState = binding.spinnerProcResult.selectedItem.toString()
        val title = binding.etBillSearch.text.toString()
        val titleTrim = title.trim()
        LogUtil.d("searchBill age:$age, procState: $procState, titleTrim:$titleTrim")

        vm.fetchBillPaging(
            billName = titleTrim,
            procResult = procState,
            age = age
        )
    }

    private fun setUpRecyclerView() {
        billSearchAdapter = BillPagingAdapter()

        billSearchAdapter.addLoadStateListener { loadState ->
            billSearchAdapter.apply {
                if (itemCount <= 0 && loadState.append is LoadState.NotLoading && loadState.append.endOfPaginationReached) {
                    LogUtil.d("noData - adapter")
                    binding.tvNoDataBill.visibility = View.VISIBLE
                } else {
                    binding.tvNoDataBill.visibility = View.GONE
                }
            }
        }

        binding.rvBillSearch.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = billSearchAdapter
        }

        billSearchAdapter.setOnItemClickListener {
            val link = it.detailLink ?: Const.DEFAULT_URL
            val action =
                BillSearchFragmentDirections.actionBillSearchFragmentToWebViewFragment(link)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setSpinner() {
        createDefaultSpinner(R.array.age, binding.spinnerAge)
        createDefaultSpinner(R.array.proc_result, binding.spinnerProcResult)

        binding.spinnerAge.onItemSelectedListener = this
        binding.spinnerProcResult.onItemSelectedListener = this
    }

    private fun createDefaultSpinner(textArrayResId: Int, spinner: Spinner) {
        ArrayAdapter.createFromResource(
            requireContext(),
            textArrayResId,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    private fun setBtnClickListener() {
        binding.ibBillSearchBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.ib_bill_search_btn -> {
                searchBill()
            }
        }
    }
}