package com.myhand.nationalassembly.data.local.member.db

import com.myhand.nationalassembly.data.local.member.model.MemberPhotoModel
import com.myhand.nationalassembly.data.remote.member.info.model.MemberInfoRow
import com.myhand.nationalassembly.data.remote.member.photo.model.MemberPhotoItem
import com.myhand.nationalassembly.data.remote.report.library.model.LibraryReportRow
import com.myhand.nationalassembly.data.remote.report.nars.model.base.NarsReportRow
import com.myhand.nationalassembly.data.remote.schedule.meeting.model.MeetingScheduleRow
import com.myhand.nationalassembly.data.remote.schedule.seminar.model.SeminarScheduleRow
import com.myhand.nationalassembly.ui.view.member.adapter.MemberInfoItem
import com.myhand.nationalassembly.ui.view.report.adapter.ReportItem
import com.myhand.nationalassembly.ui.view.schedule.adapter.MeetingScheduleItem
import com.myhand.nationalassembly.ui.view.schedule.adapter.ScheduleItem
import com.myhand.nationalassembly.ui.view.schedule.adapter.SeminarScheduleItem

fun MemberPhotoItem.toModel(): MemberPhotoModel =
    MemberPhotoModel(
        empNm = this.empNm ?: "",
        hjNm = this.hjNm ?: "",
        jpgLink = this.jpgLink ?: "",
        num = this.num ?: "",
        origNm = this.origNm ?: "",
    )

fun List<MemberPhotoItem>.toModel(): List<MemberPhotoModel> = map {
    MemberPhotoModel(
        empNm = it.empNm ?: "",
        hjNm = it.hjNm ?: "",
        jpgLink = it.jpgLink ?: "",
        num = it.num ?: "",
        origNm = it.origNm ?: "",
    )
}

fun List<MemberInfoRow>.toInfoItem(): List<MemberInfoItem> = map {
    MemberInfoItem(
        it.name,
        it.hjName,
        it.engName,
        it.bthName,
        it.bthDate,
        it.jobName,
        it.polyName,
        it.oriLocalName,
        it.electLocalName,
        it.committee,
        it.reElected,
        it.unit,
        it.sex,
        it.telNo,
        it.eMail,
        it.homepage,
        it.staff,
        it.secretary,
        it.secretary2,
        it.monaCode,
        it.memTitle,
        it.assemAddress,
        "",
    )
}

fun MemberInfoRow.toSeminarItem(): MemberInfoItem =
    MemberInfoItem(
        this.name,
        this.hjName,
        this.engName,
        this.bthName,
        this.bthDate,
        this.jobName,
        this.polyName,
        this.oriLocalName,
        this.electLocalName,
        this.committee,
        this.reElected,
        this.unit,
        this.sex,
        this.telNo,
        this.eMail,
        this.homepage,
        this.staff,
        this.secretary,
        this.secretary2,
        this.monaCode,
        this.memTitle,
        this.assemAddress,
        "",
    )

fun MemberInfoItem.toRow(): MemberInfoRow =
    MemberInfoRow(
        this.name,
        this.hjName,
        this.engName,
        this.bthName,
        this.bthDate,
        this.jobName,
        this.polyName,
        this.oriLocalName,
        this.electLocalName,
        this.committee,
        this.reElected,
        this.unit,
        this.sex,
        this.telNo,
        this.eMail,
        this.homepage,
        this.staff,
        this.secretary,
        this.secretary2,
        this.monaCode,
        this.memTitle,
        this.assemAddress,
    )

fun SeminarScheduleRow.toSeminarItem(): SeminarScheduleItem =
    SeminarScheduleItem(
        this.title,
        this.link,
        this.description,
        this.sDate,
        this.sTime,
        this.name,
        this.location,
    )

fun List<SeminarScheduleRow>.toSeminarItem(): List<SeminarScheduleItem> = map {
    SeminarScheduleItem(
        it.title,
        it.link,
        it.description,
        it.sDate,
        it.sTime,
        it.name,
        it.location,
    )
}

@JvmName("toScheduleSeminarScheduleRow")
fun List<SeminarScheduleRow>.toSchedule(): List<ScheduleItem> = map {
    ScheduleItem(
        title = it.title,
        link = it.link,
        description = it.description,
        sDate = it.sDate,
        sTime = it.sTime,
        location = it.location
    )
}

fun List<MeetingScheduleRow>.toMeetingItem(): List<MeetingScheduleItem> = map {
    MeetingScheduleItem(
        it.meetingSession,
        it.cha,
        it.title,
        it.meettingDate,
        it.meettingTime,
        it.linkUrl,
        it.unitCd
    )
}

fun List<MeetingScheduleRow>.toSchedule(): List<ScheduleItem> = map {
    ScheduleItem(
        title = it.title,
        link = it.linkUrl,
        description = it.unitNm + it.meetingSession,
        sDate = it.meettingDate,
        sTime = it.meettingTime,
        location = "본회의",
    )
}

fun List<NarsReportRow>.toItem(): List<ReportItem> = map {
    ReportItem(
        title = it.bookNm,
        link = it.pdffileUrl,
        regDate = it.insertDt,
    )
}

@JvmName("toItemLibraryReportRow")
fun List<LibraryReportRow>.toItem(): List<ReportItem> = map {
    ReportItem(
        title = it.title,
        link = it.sysattach1,
        regDate = it.regDate,
    )
}