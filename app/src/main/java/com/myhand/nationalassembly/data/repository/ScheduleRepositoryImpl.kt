package com.myhand.nationalassembly.data.repository

import com.myhand.nationalassembly.data.remote.schedule.ScheduleRemoteDataSource
import com.myhand.nationalassembly.data.remote.schedule.meeting.model.MeetingScheduleResponse
import com.myhand.nationalassembly.data.remote.schedule.seminar.model.SeminarScheduleResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleRepositoryImpl @Inject constructor(
    private val remoteDataSource: ScheduleRemoteDataSource
) : ScheduleRepository {
    //    override fun fetchSeminarSchedule(
//        numOfRows: Int?,
//        pageNo: Int?,
//        sDate: String?
//    ): Flow<PagingData<ScheduleItem>> {
//        LogUtil.d("ScheduleRepositoryImpl !!")
//
//        return remoteDataSource.fetchSeminarSchedule(
//            numOfRows,
//            pageNo,
//            sDate,
//        )
//    }
//
//    override fun fetchMeetingSchedule(
//        numOfRows: Int?,
//        pageNo: Int?,
//        mDate: String?
//    ): Flow<PagingData<ScheduleItem>> {
//        return remoteDataSource.fetchMeetingSchedule(
//            numOfRows,
//            pageNo,
//            mDate,
//        )
//    }
    override suspend fun fetchSeminarSchedule(
        numOfRows: Int?,
        pageNo: Int?,
        sDate: String?
    ): Response<SeminarScheduleResponse> {
        return remoteDataSource.fetchSeminarSchedule(
            numOfRows,
            pageNo,
            sDate,
        )
    }

    override suspend fun fetchMeetingSchedule(
        numOfRows: Int?,
        pageNo: Int?,
        mDate: String?
    ): Response<MeetingScheduleResponse> {
        return remoteDataSource.fetchMeetingSchedule(
            numOfRows,
            pageNo,
            mDate,
        )
    }
}