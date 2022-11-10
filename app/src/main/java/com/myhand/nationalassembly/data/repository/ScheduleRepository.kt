package com.myhand.nationalassembly.data.repository

import com.myhand.nationalassembly.data.remote.schedule.meeting.model.MeetingScheduleResponse
import com.myhand.nationalassembly.data.remote.schedule.seminar.model.SeminarScheduleResponse
import retrofit2.Response

interface ScheduleRepository {
//    fun fetchSeminarSchedule(
//        numOfRows: Int? = 10,
//        pageNo: Int? = 1,
//        sDate: String?
//    ): Flow<PagingData<ScheduleItem>>
//
//    fun fetchMeetingSchedule(
//        numOfRows: Int? = 10,
//        pageNo: Int? = 1,
//        mDate: String?
//    ): Flow<PagingData<ScheduleItem>>

    suspend fun fetchSeminarSchedule(
        numOfRows: Int? = 10,
        pageNo: Int? = 1,
        sDate: String?
    ): Response<SeminarScheduleResponse>

    suspend fun fetchMeetingSchedule(
        numOfRows: Int? = 10,
        pageNo: Int? = 1,
        mDate: String?
    ): Response<MeetingScheduleResponse>

}