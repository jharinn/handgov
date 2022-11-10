package com.myhand.nationalassembly.data.remote.schedule.seminar

import com.myhand.nationalassembly.data.remote.schedule.seminar.model.SeminarScheduleResponse
import com.myhand.nationalassembly.util.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SeminarScheduleApi {
    @GET("nfcoioopazrwmjrgs")
    suspend fun fetchSeminarSchedule(
        @Query("KEY") key: String = Const.OPEN_API_KEY,
        @Query("pSize") pSize: Int?,
        @Query("pIndex") pIndex: Int?,
        @Query("SDATE") sDate: String?,
    ): Response<SeminarScheduleResponse>
}