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
//    fun fetchSeminarSchedule(
//        numOfRows: Int? = 10,
//        pageNo: Int? = 1,
//        sDate: String?,
//    ): Flow<PagingData<ScheduleItem>> {
//        LogUtil.d("ScheduleRemoteDataSource !!")
//
//        val pagingSourceFactory = {
//            SeminarSchedulePagingSource(
//                api = scheduleSeminarApi,
//                numOfRows = numOfRows,
//                pageNo = pageNo,
//                sDate = sDate,
//            )
//        }
//
//        return Pager(
//            config = PagingConfig(
//                pageSize = Const.PAGING_SIZE,
//                enablePlaceholders = false,
//                maxSize = Const.PAGING_SIZE * 3
//            ),
//            pagingSourceFactory = pagingSourceFactory
//        ).flow
//    }
//
//    fun fetchMeetingSchedule(
//        numOfRows: Int? = 10,
//        pageNo: Int? = 1,
//        mDate: String?,
//    ): Flow<PagingData<ScheduleItem>> {
//        val pagingSourceFactory = {
//            MeetingSchedulePagingSource(
//                api = meetingScheduleApi,
//                numOfRows = numOfRows,
//                pageNo = pageNo,
//                meetingDate = mDate,
//            )
//        }
//
//        return Pager(
//            config = PagingConfig(
//                pageSize = Const.PAGING_SIZE,
//                enablePlaceholders = false,
//                maxSize = Const.PAGING_SIZE * 3
//            ),
//            pagingSourceFactory = pagingSourceFactory
//        ).flow
//    }
}