package com.myhand.nationalassembly.ui.view.bill.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.myhand.nationalassembly.data.remote.bill.openapi.model.BillLinkRow
import com.myhand.nationalassembly.data.repository.BillRepository
import com.myhand.nationalassembly.util.LogUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BillViewModel @Inject constructor(
    private val billRepository: BillRepository
) : ViewModel() {

    // NARS 이슈 동향
    private val _fetchBillDataResult =
        MutableStateFlow<PagingData<BillLinkRow>>(PagingData.empty())
    val fetchBillDataResult: StateFlow<PagingData<BillLinkRow>> = _fetchBillDataResult.asStateFlow()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun fetchBillPaging(
        pSize: Int? = 10,
        pIndex: Int? = 1,
        billId: String? = null,
        billNo: String? = null,
        billName: String? = null,
        committee: String? = null,
        procResult: String? = null,
        age: String? = "21",
        proposer: String? = null,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            showLoading()

            billRepository.searchBillLinkPaging(
                pSize = pSize,
                pIndex = pIndex,
                billId = billId,
                billNo = billNo,
                billName = billName,
                committee = committee,
                procResult = procResult,
                age = age,
                proposer = proposer,
            )
                .cachedIn(viewModelScope)
                .collect {
                    _fetchBillDataResult.value = it
                    hideLoading()
                }
        }
    }


    suspend fun showLoading() {
        withContext(Dispatchers.Main) {
            LogUtil.d("showLoading")
            isLoading.value = true

            return@withContext
        }
    }

    suspend fun hideLoading() {
        withContext(Dispatchers.Main) {
            LogUtil.d("hideLoading")
            isLoading.value = false

            return@withContext
        }
    }
}