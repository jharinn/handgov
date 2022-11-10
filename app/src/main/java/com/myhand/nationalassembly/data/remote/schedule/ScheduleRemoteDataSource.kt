package com.myhand.nationalassembly.data.remote.schedule

import com.myhand.nationalassembly.data.remote.schedule.meeting.MeetingScheduleApi
import com.myhand.nationalassembly.data.remote.schedule.meeting.model.MeetingScheduleResponse
import com.myhand.nationalassembly.data.remote.schedule.seminar.SeminarScheduleApi
import com.myhand.nationalassembly.data.remote.schedule.seminar.model.SeminarScheduleResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleRemoteDataSource @Inject constructor(
    private val scheduleSeminarApi: SeminarScheduleApi,
    private val meetingScheduleApi: MeetingScheduleApi
) {
    suspend fun fetchSeminarSchedule(
        numOfRows: Int? = 10,
        pageNo: Int? = 1,
        sDate: String?,
    ): Response<SeminarScheduleResponse> {
        return scheduleSeminarApi.fetchSeminarSchedule(
            pSize = numOfRows,
            pIndex = pageNo,
            sDate = sDate,
        )
    }

    suspend fun fetchMeetingSchedule(
        numOfRows: Int? = 10,
        pageNo: Int? = 1,
        mDate: String?,
    ): Response<MeetingScheduleResponse> {
        return meetingScheduleApi.fetchMeetingSchedule(
            pSize = numOfRows,
            pIndex = pageNo,
            meetingDate = mDate,
        )
    }
}