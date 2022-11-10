package com.myhand.nationalassembly.data.remote.report.library

import com.myhand.nationalassembly.data.remote.report.library.model.LibraryReportResponse
import com.myhand.nationalassembly.util.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LibraryReportApi {
    @GET("nzkdlzgoadvnlcubt")
    suspend fun fetchLibraryReport(
        @Query("KEY") key: String = Const.OPEN_API_KEY,
        @Query("pSize") pSize: Int?,
        @Query("pIndex") pIndex: Int?,
    ): Response<LibraryReportResponse>
}