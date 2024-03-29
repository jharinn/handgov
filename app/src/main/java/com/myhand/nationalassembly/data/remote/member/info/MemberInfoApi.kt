package com.myhand.nationalassembly.data.remote.member.info

import com.myhand.nationalassembly.data.remote.member.info.model.MemberInfoResponse
import com.myhand.nationalassembly.util.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MemberInfoApi {
    @GET("nwvrqwxyaytdsfvhu")
    suspend fun searchMember(
        @Query("KEY") key: String = Const.OPEN_API_KEY,
        @Query("pSize") pSize: Int?,
        @Query("pIndex") pIndex: Int?,
        @Query("HG_NM") name: String?,
        @Query("POLY_NM") partyName: String?,
        @Query("ORIG_NM") origName: String?,
    ): Response<MemberInfoResponse>
}