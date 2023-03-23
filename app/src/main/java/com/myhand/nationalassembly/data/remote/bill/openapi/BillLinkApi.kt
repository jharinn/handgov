package com.myhand.nationalassembly.data.remote.bill.openapi

import com.myhand.nationalassembly.data.remote.bill.openapi.model.BillLinkResponse
import com.myhand.nationalassembly.util.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BillLinkApi {
    @GET("nzmimeepazxkubdpn")
    suspend fun fetchBillLinkResult(
        @Query("KEY") key: String = Const.OPEN_API_KEY,
        @Query("pSize") pSize: Int?,
        @Query("pIndex") pIndex: Int?,
        @Query("BILL_ID")
        billId: String?,
        @Query("BILL_NO")
        billNo: String?,
        @Query("BILL_NAME")
        billName: String?,
        @Query("COMMITTEE")
        committee: String?,
        @Query("PROC_RESULT")
        procResult: String?,
        @Query("AGE")
        age: String?,
        @Query("PROPOSER")
        proposer: String?,
    ): Response<BillLinkResponse>
}