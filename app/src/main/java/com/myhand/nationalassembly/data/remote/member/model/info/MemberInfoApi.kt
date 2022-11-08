package com.myhand.nationalassembly.data.remote.member.model.info

import com.myhand.nationalassembly.util.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MemberInfoApi {
    @GET("nwvrqwxyaytdsfvhu")
    suspend fun searchMember(
        @Query("KEY") key: String = Const.MEMBER_INFO_API_KEY,
        @Query("pSize") pSize: Int?,
        @Query("pIndex") pIndex: Int?,
    ): Response<MemberInfoResponse>
}