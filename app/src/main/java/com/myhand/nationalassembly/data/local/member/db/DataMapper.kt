package com.myhand.nationalassembly.data.local.member.db

import com.myhand.nationalassembly.data.local.member.model.MemberPhotoModel
import com.myhand.nationalassembly.data.remote.member.model.info.MemberInfoRow
import com.myhand.nationalassembly.data.remote.member.model.photo.MemberPhotoItem
import com.myhand.nationalassembly.ui.view.member.adapter.MemberInfoItem

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