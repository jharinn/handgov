package com.myhand.nationalassembly.data.local.member.db

import com.myhand.nationalassembly.data.local.member.model.MemberPhotoModel
import com.myhand.nationalassembly.data.remote.member.model.info.MemberInfoRow
import com.myhand.nationalassembly.data.remote.member.model.photo.MemberPhotoItem
import com.myhand.nationalassembly.ui.view.member.adapter.MemberInfoItem

fun MemberPhotoModel.toItem(): MemberPhotoItem =
    MemberPhotoItem(
        deptCd = this.deptCd,
        empNm = this.empNm,
        engNm = this.engNm,
        hjNm = this.hjNm,
        jpgLink = this.jpgLink,
        num = this.num,
        origNm = this.origNm,
        reeleGbnNm = this.reeleGbnNm,
    )

fun List<MemberPhotoModel>.toItem(): List<MemberPhotoItem> = map {
    MemberPhotoItem(
        deptCd = it.deptCd,
        empNm = it.empNm,
        engNm = it.engNm,
        hjNm = it.hjNm,
        jpgLink = it.jpgLink,
        num = it.num,
        origNm = it.origNm,
        reeleGbnNm = it.reeleGbnNm,
    )
}

fun MemberPhotoItem.toModel(): MemberPhotoModel =
    MemberPhotoModel(
        deptCd = this.deptCd ?: "",
        empNm = this.empNm ?: "",
        engNm = this.engNm ?: "",
        hjNm = this.hjNm ?: "",
        jpgLink = this.jpgLink ?: "",
        num = this.num ?: "",
        origNm = this.origNm ?: "",
        reeleGbnNm = this.reeleGbnNm ?: "",
    )

fun List<MemberPhotoItem>.toModel(): List<MemberPhotoModel> = map {
    MemberPhotoModel(
        deptCd = it.deptCd ?: "",
        empNm = it.empNm ?: "",
        engNm = it.engNm ?: "",
        hjNm = it.hjNm ?: "",
        jpgLink = it.jpgLink ?: "",
        num = it.num ?: "",
        origNm = it.origNm ?: "",
        reeleGbnNm = it.reeleGbnNm ?: "",
    )
}

fun List<MemberInfoRow>.toInfoItem(): List<MemberInfoItem> = map {
    MemberInfoItem(
        it.name,
        it.hjName,
        it.engName,
        it.BthName,
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

fun MemberInfoRow.toItem(): MemberInfoItem =
    MemberInfoItem(
        this.name,
        this.hjName,
        this.engName,
        this.BthName,
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
        this.BthName,
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