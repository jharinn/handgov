package com.myhand.nationalassembly.data.remote.schedule.meeting

import com.myhand.nationalassembly.data.remote.schedule.meeting.model.MeetingScheduleResponse
import com.myhand.nationalassembly.util.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MeetingScheduleApi {
    @GET("nekcaiymatialqlxr")
    suspend fun fetchMeetingSchedule(
        @Query("KEY") key: String = Const.OPEN_API_KEY,
        @Query("pSize") pSize: Int?,
        @Query("pIndex") pIndex: Int?,
        @Query("MEETTING_DATE") meetingDate: String?,
        @Query("UNIT_CD") unitCd: String? = "100021",
    ): Response<MeetingScheduleResponse>
}