package com.myhand.nationalassembly.ui.view.schedule.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeminarScheduleItem(
    /** 새정부 공공기관 혁신방향 및 과제 토론회 */
    var title: String? = null,
    /** https:ampos.nanet.go.kr:7443/seminarList.do#fromDate=20221125&endDate=20221125&searchGubun=search */
    var link: String? = null,
    /**  ▶ 주최 : 류성걸 의원실 ▶ 일시 : 2022.11.25(14:00 ~ 20:22) ▶ 장소 : 의원회관 제1소회의실 */
    var description: String? = null,
    /** 2022.11.25 */
    var sDate: String? = null,
    var sTime: String? = null,
    /** 류성걸 의원실 */
    var name: String? = null,
    /** 의원회관 제1소회의실 */
    var location: String? = null,
) : Parcelable