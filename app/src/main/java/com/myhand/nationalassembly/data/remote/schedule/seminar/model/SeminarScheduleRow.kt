package com.myhand.nationalassembly.data.remote.schedule.seminar.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "row", strict = false)
data class SeminarScheduleRow @JvmOverloads constructor(
    /** 새정부 공공기관 혁신방향 및 과제 토론회 */
    @field:Element(name = "TITLE", required = false)
    var title: String? = null,
    /** https:ampos.nanet.go.kr:7443/seminarList.do#fromDate=20221125&endDate=20221125&searchGubun=search */
    @field:Element(name = "LINK", required = false)
    var link: String? = null,
    /**  ▶ 주최 : 류성걸 의원실 ▶ 일시 : 2022.11.25(14:00 ~ 20:22) ▶ 장소 : 의원회관 제1소회의실 */
    @field:Element(name = "DESCRIPTION", required = false)
    var description: String? = null,
    /** 2022.11.25 */
    @field:Element(name = "SDATE", required = false)
    var sDate: String? = null,
    /** 14:00~20:22 */
    @field:Element(name = "STIME", required = false)
    var sTime: String? = null,
    /** 류성걸 의원실 */
    @field:Element(name = "NAME", required = false)
    var name: String? = null,
    /** 의원회관 제1소회의실 */
    @field:Element(name = "LOCATION", required = false)
    var location: String? = null,
)