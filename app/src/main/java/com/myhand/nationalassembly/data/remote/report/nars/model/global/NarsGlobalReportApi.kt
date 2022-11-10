package com.myhand.nationalassembly.data.remote.report.nars.model.global

import com.myhand.nationalassembly.util.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NarsGlobalReportApi {
    @GET("nhtegpibasggyssce")
    suspend fun fetchGlobalReport(
        @Query("KEY") key: String = Const.OPEN_API_KEY,
        @Query("pSize") pSize: Int?,
        @Query("pIndex") pIndex: Int?,
    ): Response<NarsGlobalReportResponse>
}