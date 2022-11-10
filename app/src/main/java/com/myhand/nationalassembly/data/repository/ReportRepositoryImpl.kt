package com.myhand.nationalassembly.data.repository

import androidx.paging.PagingData
import com.myhand.nationalassembly.data.remote.report.ReportDataSource
import com.myhand.nationalassembly.data.remote.report.library.model.LibraryReportResponse
import com.myhand.nationalassembly.data.remote.report.nars.model.global.NarsGlobalReportResponse
import com.myhand.nationalassembly.ui.view.report.adapter.ReportItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReportRepositoryImpl @Inject constructor(
    private val remoteDataSource: ReportDataSource
) : ReportRepository {
    override fun fetchIssueReportPaging(
        numOfRows: Int?,
        pageNo: Int?,
    ): Flow<PagingData<ReportItem>> = remoteDataSource.fetchIssueReportPaging(
        numOfRows, pageNo
    )

    override fun fetchPolicyReportPaging(
        numOfRows: Int?,
        pageNo: Int?
    ): Flow<PagingData<ReportItem>> = remoteDataSource.fetchPolicyReportPaging(
        numOfRows, pageNo
    )

    override suspend fun fetchGlobalReport(
        numOfRows: Int?,
        pageNo: Int?
    ): Response<NarsGlobalReportResponse> = remoteDataSource.fetchGlobalReport(
        numOfRows, pageNo
    )

    override suspend fun fetchLibraryReport(
        numOfRows: Int?,
        pageNo: Int?
    ): Response<LibraryReportResponse> = remoteDataSource.fetchLibraryReport(
        numOfRows, pageNo
    )


}