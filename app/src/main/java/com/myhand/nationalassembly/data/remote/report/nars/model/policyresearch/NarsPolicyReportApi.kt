package com.myhand.nationalassembly.data.remote.report.nars.model.policyresearch

import com.myhand.nationalassembly.util.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NarsPolicyReportApi {
    @GET("nijtjlghaowvisahk")
    suspend fun fetchPolicyReport(
        @Query("KEY") key: String = Const.OPEN_API_KEY,
        @Query("pSize") pSize: Int?,
        @Query("pIndex") pIndex: Int?,
    ): Response<NarsPolicyReportResponse>
}