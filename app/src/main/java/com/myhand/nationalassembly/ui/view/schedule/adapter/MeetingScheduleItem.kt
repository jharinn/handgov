package com.myhand.nationalassembly.ui.view.schedule.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MeetingScheduleItem(
    /** 제400회국회(정기회) */
    var meetingSession: String? = null,
    /** 제8차 */
    var cha: String? = null,
    /** 제400-8차(의사일정) */
    var title: String? = null,
    /** 2022-09-28 */
    var meettingDate: String? = null,
    /** 10:00 */
    var meettingTime: String? = null,
    /** https://www.assembly.go.kr/portal/na/agenda/agendaSchl.do?menuNo=600015&scheduleDivCd=ASSEM&uniqId=1100018026 */
    var linkUrl: String? = null,
    /** 제21대 */
    var unitNm: String? = null,
) : Parcelable