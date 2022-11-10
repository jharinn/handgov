package com.myhand.nationalassembly.data.remote.member.info.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "row", strict = false)
data class MemberInfoRow @JvmOverloads constructor(
    /** 강기윤 */
    @field:Element(name = "HG_NM", required = false)
    var name: String? = null,
    /** 姜起潤 */
    @field:Element(name = "HJ_NM", required = false)
    var hjName: String? = null,
    /** KANG GIYUN */
    @field:Element(name = "ENG_NM", required = false)
    var engName: String? = null,
    /** 음 */
    @field:Element(name = "BTH_GBN_NM", required = false)
    var bthName: String? = null,
    /** 1960-06-04 */
    @field:Element(name = "BTH_DATE", required = false)
    var bthDate: String? = null,
    /** 간사 */
    @field:Element(name = "JOB_RES_NM", required = false)
    var jobName: String? = null,
    /** 국민의힘 */
    @field:Element(name = "POLY_NM", required = false)
    var polyName: String? = null,
    /** 경남 창원시성산구 */
    @field:Element(name = "ORIG_NM", required = false)
    var oriLocalName: String? = null,
    /** 지역구 */
    @field:Element(name = "ELECT_GBN_NM", required = false)
    var electLocalName: String? = null,
    /** 보건복지위원회, 연금개혁특별위원회*/
    @field:Element(name = "CMITS", required = false)
    var committee: String? = null,
    /** 재선 */
    @field:Element(name = "REELE_GBN_NM", required = false)
    var reElected: String? = null,
    /**  제19대, 제21대 */
    @field:Element(name = "UNITS", required = false)
    var unit: String? = null,
    /** 남 */
    @field:Element(name = "SEX_GBN_NM", required = false)
    var sex: String? = null,
    /** 02-784-1751 */
    @field:Element(name = "TEL_NO", required = false)
    var telNo: String? = null,
    @field:Element(name = "E_MAIL", required = false)
    /** ggotop@naver.com */
    var eMail: String? = null,
    @field:Element(name = "HOMEPAGE", required = false)
    /** http://blog.naver.com/ggotop */
    var homepage: String? = null,
    @field:Element(name = "STAFF", required = false)
    /** 김홍광, 한영애 */
    var staff: String? = null,
    /** 김영진, 지상훈 */
    @field:Element(name = "SECRETARY", required = false)
    var secretary: String? = null,
    /** 안효상, 홍지형, 이유진, 김지훈, 조옥자 */
    @field:Element(name = "SECRETARY2", required = false)
    var secretary2: String? = null,
    /** 14M56632 */
    @field:Element(name = "MONA_CD", required = false)
    var monaCode: String? = null,
    /** <![CDATA[ [학력] 마산공고(26회, required = false) 창원대학교 행정학과 */
    @field:Element(name = "MEM_TITLE", required = false)
    var memTitle: String? = null,
    /** 의원회관 937호 */
    @field:Element(name = "ASSEM_ADDR", required = false)
    var assemAddress: String? = null
)