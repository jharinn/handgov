package com.myhand.nationalassembly.ui.view.report.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.myhand.nationalassembly.data.local.member.db.toItem
import com.myhand.nationalassembly.data.repository.ReportRepository
import com.myhand.nationalassembly.ui.view.report.adapter.ReportItem
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
class ReportBudgetViewModel @Inject constructor(
    private val reportRepository: ReportRepository
) : ViewModel() {

    // NARS 이슈 동향
    private val _fetchIssueReport =
        MutableStateFlow<PagingData<ReportItem>>(PagingData.empty())
    val fetchIssueReport: StateFlow<PagingData<ReportItem>> = _fetchIssueReport.asStateFlow()

    // NARS 정책연구
    private val _fetchPolicyReport =
        MutableStateFlow<PagingData<ReportItem>>(PagingData.empty())
    val fetchPolicyReport: StateFlow<PagingData<ReportItem>> = _fetchPolicyReport.asStateFlow()

    // NARS 글로벌
    private val _fetchGlobalReport = MutableLiveData<List<ReportItem>>()
    val fetchGlobalReport: LiveData<List<ReportItem>> = _fetchGlobalReport

    // 국회도서관 정책연구
    private val _fetchLibraryReport = MutableLiveData<List<ReportItem>>()
    val fetchLibraryReport: LiveData<List<ReportItem>> = _fetchLibraryReport

    val isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun fetchIssuePaging(
        numOfRows: Int? = 10,
        pageNo: Int? = 1,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            showLoading()

            reportRepository.fetchIssueReportPaging(
                numOfRows,
                pageNo,
            )
                .cachedIn(viewModelScope)
                .collect {
                    _fetchIssueReport.value = it
                    hideLoading()
                }
        }
    }

    fun fetchPolicyPaging(
        numOfRows: Int? = 10,
        pageNo: Int? = 1,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            showLoading()

            reportRepository.fetchPolicyReportPaging(
                numOfRows,
                pageNo,
            )
                .cachedIn(viewModelScope)
                .collect {
                    _fetchPolicyReport.value = it
                    hideLoading()
                }
        }
    }

    fun fetchGlobalReport(numOfRows: Int?, pageNo: Int?) = viewModelScope.launch(Dispatchers.IO) {
        val response = reportRepository.fetchGlobalReport(
            numOfRows,
            pageNo,
        )

        if (response.isSuccessful) {
            response.body()?.let { body ->
                val row = body.row ?: listOf()
                _fetchGlobalReport.postValue(row.toItem())
            }
        }
    }

    fun fetchLibraryReport(numOfRows: Int?, pageNo: Int?) = viewModelScope.launch(Dispatchers.IO) {
        val response = reportRepository.fetchLibraryReport(
            numOfRows,
            pageNo,
        )

        if (response.isSuccessful) {
            response.body()?.let { body ->
                val row = body.row ?: listOf()
                _fetchLibraryReport.postValue(row.toItem())
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