package com.myhand.nationalassembly.ui.view.schedule.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myhand.nationalassembly.data.local.member.db.toSchedule
import com.myhand.nationalassembly.data.remote.schedule.meeting.model.MeetingScheduleResponse
import com.myhand.nationalassembly.data.remote.schedule.seminar.model.SeminarScheduleResponse
import com.myhand.nationalassembly.data.repository.ScheduleRepository
import com.myhand.nationalassembly.ui.view.schedule.adapter.ScheduleItem
import com.myhand.nationalassembly.util.LogUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {
    private val _fetchSeminarResult = MutableLiveData<SeminarScheduleResponse>()
    val fetchSeminarResult: LiveData<SeminarScheduleResponse> get() = _fetchSeminarResult

    private val _fetchMeetingResult = MutableLiveData<MeetingScheduleResponse>()
    val fetchMeetingResult: LiveData<MeetingScheduleResponse> get() = _fetchMeetingResult

    val _fetchScheduleResult = MutableLiveData<List<ScheduleItem>>()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    // Api
    fun getSchedules(
        numOfRows: Int? = 10,
        pageNo: Int? = 1,
        date: String?,
        month: String?,
        year: String?
    ) = viewModelScope.launch(Dispatchers.IO) {
        val sDate = "$year.$month.$date" //SDATE='2007.01.12'
        val mDate = "$year-$month-$date" //	MEETTING_DATE='2021-11-11'
        LogUtil.d("fetchSchedules !! sDate: $sDate, mDate: $mDate")

        withContext(Dispatchers.IO) {

            val seminarResponse = scheduleRepository.fetchSeminarSchedule(
                numOfRows,
                pageNo,
                sDate,
            )

            if (seminarResponse.isSuccessful) {
                seminarResponse.body()?.let { body ->
                    _fetchSeminarResult.postValue(body)
                }
            }
        }
        withContext(Dispatchers.IO) {
            val meetingResponse = scheduleRepository.fetchMeetingSchedule(
                numOfRows,
                pageNo,
                mDate,
            )

            if (meetingResponse.isSuccessful) {
                meetingResponse.body()?.let { body ->
                    _fetchMeetingResult.postValue(body)
                }
            }
        }

        withContext(Dispatchers.IO) {
            val seminarList = _fetchSeminarResult.value?.row?.toSchedule() ?: listOf<ScheduleItem>()
            val meetingList = fetchMeetingResult.value?.row?.toSchedule() ?: listOf<ScheduleItem>()
            val scheduleList = seminarList.plus(meetingList)

            _fetchScheduleResult.postValue(scheduleList)
        }
    }


//    fun fetchSchedules(
//        numOfRows: Int? = 10,
//        pageNo: Int? = 1,
//        date: String?,
//        month: String?,
//        year: String?
//    ) {
//        val sDate = "$year.$month.$date" //SDATE='2007.01.12'
//        val mDate = "$year-$month-$date" //	MEETTING_DATE='2021-11-11'
//        LogUtil.d("fetchSchedules !! sDate: $sDate, mDate: $mDate")
//
//        viewModelScope.launch(Dispatchers.IO) {
//            showLoading()
//
//            scheduleRepository.fetchSeminarSchedule(
//                numOfRows,
//                pageNo,
//                sDate,
//            )
//                .cachedIn(viewModelScope)
//                .collect {
//                    _fetchSeminarResult.value = it
//                    it.map {
//                        LogUtil.d("_fetchSeminarResult :: ${it.title}!")
//                    }
//                }
//
//            withContext(Dispatchers.IO) {
//                scheduleRepository.fetchMeetingSchedule(
//                    numOfRows,
//                    pageNo,
//                    mDate,
//                )
//                    .cachedIn(viewModelScope)
//                    .collect {
//                        _fetchMeetingResult.value = it
//                        it.map {
//                            LogUtil.d("_fetchMeetingResult :: ${it.title}!")
//                        }
//                    }
//
//                LogUtil.d("scheduleRepository.fetchMeetingSchedule !!")
//
////                merge(fetchSeminarResult, fetchMeetingResult).collect {
////                    _fetchScheduleResult.value = it
////                }
//            }
//
//
//        }
//    }

    suspend fun showLoading() {
        withContext(Dispatchers.Main) {
            LogUtil.d("showLoading")
            isLoading.value = true
        }
    }

    suspend fun hideLoading() {
        withContext(Dispatchers.Main) {
            LogUtil.d("hideLoading")
            isLoading.value = false
        }
    }
}