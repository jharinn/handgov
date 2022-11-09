package com.myhand.nationalassembly.data.remote.member.model.info

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "row")
data class MemberInfoRow(
    /** 강기윤 */
    @PropertyElement(name = "HG_NM")
    val name: String?,
    /** 姜起潤 */
    @PropertyElement(name = "HJ_NM")
    val hjName: String?,
    /** KANG GIYUN */
    @PropertyElement(name = "ENG_NM")
    val engName: String?,
    /** 음 */
    @PropertyElement(name = "BTH_GBN_NM")
    val bthName: String?,
    /** 1960-06-04 */
    @PropertyElement(name = "BTH_DATE")
    val bthDate: String?,
    /** 간사 */
    @PropertyElement(name = "JOB_RES_NM")
    val jobName: String?,
    /** 국민의힘 */
    @PropertyElement(name = "POLY_NM")
    val polyName: String?,
    /** 경남 창원시성산구 */
    @PropertyElement(name = "ORIG_NM")
    val oriLocalName: String?,
    /** 지역구 */
    @PropertyElement(name = "ELECT_GBN_NM")
    val electLocalName: String?,
    /** 보건복지위원회, 연금개혁특별위원회*/
    @PropertyElement(name = "CMITS")
    val committee: String?,
    /** 재선 */
    @PropertyElement(name = "REELE_GBN_NM")
    val reElected: String?,
    /**  제19대, 제21대 */
    @PropertyElement(name = "UNITS")
    val unit: String?,
    /** 남 */
    @PropertyElement(name = "SEX_GBN_NM")
    val sex: String?,
    /** 02-784-1751 */
    @PropertyElement(name = "TEL_NO")
    val telNo: String?,
    @PropertyElement(name = "E_MAIL")
    /** ggotop@naver.com */
    val eMail: String?,
    @PropertyElement(name = "HOMEPAGE")
    /** http://blog.naver.com/ggotop */
    val homepage: String?,
    @PropertyElement(name = "STAFF")
    /** 김홍광, 한영애 */
    val staff: String?,
    /** 김영진, 지상훈 */
    @PropertyElement(name = "SECRETARY")
    val secretary: String?,
    /** 안효상, 홍지형, 이유진, 김지훈, 조옥자 */
    @PropertyElement(name = "SECRETARY2")
    val secretary2: String?,
    /** 14M56632 */
    @PropertyElement(name = "MONA_CD")
    val monaCode: String?,
    /** <![CDATA[ [학력] 마산공고(26회) 창원대학교 행정학과 */
    @PropertyElement(name = "MEM_TITLE")
    val memTitle: String?,
    /** 의원회관 937호 */
    @PropertyElement(name = "ASSEM_ADDR")
    val assemAddress: String?
)