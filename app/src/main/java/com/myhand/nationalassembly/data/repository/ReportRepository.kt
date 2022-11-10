package com.myhand.nationalassembly.data.repository

import androidx.paging.PagingData
import com.myhand.nationalassembly.data.remote.report.library.model.LibraryReportResponse
import com.myhand.nationalassembly.data.remote.report.nars.model.global.NarsGlobalReportResponse
import com.myhand.nationalassembly.ui.view.report.adapter.ReportItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ReportRepository {
    fun fetchIssueReportPaging(
        numOfRows: Int? = 10,
        pageNo: Int? = 1,
    ): Flow<PagingData<ReportItem>>

    fun fetchPolicyReportPaging(
        numOfRows: Int? = 10,
        pageNo: Int? = 1,
    ): Flow<PagingData<ReportItem>>

    suspend fun fetchGlobalReport(
        numOfRows: Int? = 10,
        pageNo: Int? = 1,
    ): Response<NarsGlobalReportResponse>

    suspend fun fetchLibraryReport(
        numOfRows: Int? = 10,
        pageNo: Int? = 1,
    ): Response<LibraryReportResponse>
}