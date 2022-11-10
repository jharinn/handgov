package com.myhand.nationalassembly.data.remote.schedule.meeting.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "row", strict = false)
data class MeetingScheduleRow @JvmOverloads constructor(
    /** 제400회국회(정기회) */
    @field:Element(name = "MEETINGSESSION", required = false)
    var meetingSession: String? = null,
    /** 제8차 */
    @field:Element(name = "CHA", required = false)
    var cha: String? = null,
    /** 제400-8차(의사일정) */
    @field:Element(name = "TITLE", required = false)
    var title: String? = null,
    /** 2022-09-28 */
    @field:Element(name = "MEETTING_DATE", required = false)
    var meettingDate: String? = null,
    /** 10:00 */
    @field:Element(name = "MEETTING_TIME", required = false)
    var meettingTime: String? = null,
    /** https://www.assembly.go.kr/portal/na/agenda/agendaSchl.do?menuNo=600015&scheduleDivCd=ASSEM&uniqId=1100018026 */
    @field:Element(name = "LINK_URL", required = false)
    var linkUrl: String? = null,
    /** 100021 */
    @field:Element(name = "UNIT_CD", required = false)
    var unitCd: String? = null,
    /** 제21대 */
    @field:Element(name = "UNIT_NM", required = false)
    var unitNm: String? = null,
)
