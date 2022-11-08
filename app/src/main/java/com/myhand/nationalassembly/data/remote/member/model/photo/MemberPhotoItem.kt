package com.myhand.nationalassembly.data.remote.member.model.photo

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "Item")
data class MemberPhotoItem(
    /** 9771230 */
    @PropertyElement(name = "deptCd") var deptCd: String?,
    /** 강기윤 */
    @PropertyElement(name = "empNm") var empNm: String?,
    /** KANG GIYUN */
    @PropertyElement(name = "engNm") var engNm: String?,
    /** 姜起潤 */
    @PropertyElement(name = "hjNm") var hjNm: String?,
    /** http://www.assembly.go.kr/photo/9771230.jpg */
    @PropertyElement(name = "jpgLink") var jpgLink: String?,
    /** 2685 */
    @PropertyElement(name = "num") var num: String?,
    /** 경남 창원시성산구 */
    @PropertyElement(name = "origNm") var origNm: String?,
    /** 재선 */
    @PropertyElement(name = "reeleGbnNm") var reeleGbnNm: String?,
)
