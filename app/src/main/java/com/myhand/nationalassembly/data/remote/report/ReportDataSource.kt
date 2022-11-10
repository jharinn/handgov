package com.myhand.nationalassembly.data.remote.report

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.myhand.nationalassembly.data.remote.report.library.LibraryReportApi
import com.myhand.nationalassembly.data.remote.report.library.model.LibraryReportResponse
import com.myhand.nationalassembly.data.remote.report.nars.model.global.NarsGlobalReportApi
import com.myhand.nationalassembly.data.remote.report.nars.model.global.NarsGlobalReportResponse
import com.myhand.nationalassembly.data.remote.report.nars.model.issue.IssueReportPagingSource
import com.myhand.nationalassembly.data.remote.report.nars.model.issue.NarsIssueReportApi
import com.myhand.nationalassembly.data.remote.report.nars.model.policyresearch.NarsPolicyReportApi
import com.myhand.nationalassembly.data.remote.report.nars.model.policyresearch.NarsPolicyReportPagingSource
import com.myhand.nationalassembly.ui.view.report.adapter.ReportItem
import com.myhand.nationalassembly.util.Const
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReportDataSource @Inject constructor(
    private val issueReportApi: NarsIssueReportApi,
    private val narsPolicyReportApi: NarsPolicyReportApi,
    private val narsGlobalReportApi: NarsGlobalReportApi,
    private val libraryReportApi: LibraryReportApi
) {
    fun fetchIssueReportPaging(
        numOfRows: Int? = 10,
        pageNo: Int? = 1,
    ): Flow<PagingData<ReportItem>> {
        val pagingSourceFactory = {
            IssueReportPagingSource(
                issueReportApi,
                numOfRows,
                pageNo,
            )
        }

        return Pager(
            config = PagingConfig(
                pageSize = Const.PAGING_SIZE,
                enablePlaceholders = false,
                maxSize = Const.PAGING_SIZE * 3
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun fetchPolicyReportPaging(
        numOfRows: Int? = 10,
        pageNo: Int? = 1,
    ): Flow<PagingData<ReportItem>> {
        val pagingSourceFactory = {
            NarsPolicyReportPagingSource(
                narsPolicyReportApi,
                numOfRows,
                pageNo,
            )
        }

        return Pager(
            config = PagingConfig(
                pageSize = Const.PAGING_SIZE,
                enablePlaceholders = false,
                maxSize = Const.PAGING_SIZE * 3
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    suspend fun fetchGlobalReport(
        numOfRows: Int? = 10,
        pageNo: Int? = 1,
    ): Response<NarsGlobalReportResponse> =
        narsGlobalReportApi.fetchGlobalReport(pSize = numOfRows, pIndex = pageNo)

    suspend fun fetchLibraryReport(
        numOfRows: Int? = 10,
        pageNo: Int? = 1,
    ): Response<LibraryReportResponse> =
        libraryReportApi.fetchLibraryReport(pSize = numOfRows, pIndex = pageNo)
}