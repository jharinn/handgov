package com.myhand.nationalassembly.ui.view.member.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MemberInfoItem(
    /** 강기윤 */
    val name: String?,
    /** 姜起潤 */
    val hjName: String?,
    /** KANG GIYUN */
    val engName: String?,
    /** 음 */
    val bthName: String?,
    /** 1960-06-04 */
    val bthDate: String?,
    /** 간사 */
    val jobName: String?,
    /** 국민의힘 */
    val polyName: String?,
    /** 경남 창원시성산구 */
    val oriLocalName: String?,
    /** 지역구 */
    val electLocalName: String?,
    /** 보건복지위원회, 연금개혁특별위원회*/
    val committee: String?,
    /** 재선 */
    val reElected: String?,
    /**  제19대, 제21대 */
    val unit: String?,
    /** 남 */
    val sex: String?,
    /** 02-784-1751 */
    val telNo: String?,
    /** ggotop@naver.com */
    val eMail: String?,
    /** http://blog.naver.com/ggotop */
    val homepage: String?,
    /** 김홍광, 한영애 */
    val staff: String?,
    /** 김영진, 지상훈 */
    val secretary: String?,
    /** 안효상, 홍지형, 이유진, 김지훈, 조옥자 */
    val secretary2: String?,
    /** 14M56632 */
    val monaCode: String?,
    /** <![CDATA[ [학력] 마산공고(26회) 창원대학교 행정학과 */
    val memTitle: String?,
    /** 의원회관 937호 */
    val assemAddress: String?,
    /** 사진 링크 */
    var photoLink: String?
) : Parcelable