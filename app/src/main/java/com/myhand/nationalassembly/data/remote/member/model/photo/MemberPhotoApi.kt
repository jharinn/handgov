package com.myhand.nationalassembly.data.remote.member.model.photo

import com.myhand.nationalassembly.util.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MemberPhotoApi {
    @GET("NationalAssemblyInfoService/getMemberCurrStateList")
    suspend fun getAllMemberPhoto(
        @Query("ServiceKey") serviceKey: String = Const.MEMBER_PHOTO_API_KEY,
        @Query("numOfRows") numOfRows: Int? = 300,
        @Query("pageNo") pageNo: Int? = 1,
    ): Response<MemberPhotoResponse>
}