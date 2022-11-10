package com.myhand.nationalassembly.data.remote.report.nars.model.base

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "row", strict = false)
data class NarsReportRow @JvmOverloads constructor(
    /**  http://www.nars.go.kr/fileDownload2.do?doc_id=1OGA8JAvAiO&fileName=(%EC%9D%B4%EC%8A%88%EC%99%80%EB%85%BC%EC%A0%90%202007%ED%98%B8-20221025)%EB%B6%81%ED%95%9C%20%ED%95%B5%EB%AC%B4%EB%A0%A5%20%EB%B2%95%EC%A0%9C%ED%99%94%EC%9D%98%20%ED%95%A8%EC%9D%98%EC%99%80%20%EB%8C%80%EC%9D%91%20%EB%B0%A9%ED%96%A5.pdf */
    @field:Element(name = "PDFFILEURL", required = false)
    var pdffileUrl: String? = null,
    /** http://drm.nars.go.kr:7003/sd/imageviewer?DocId=1OGA8JAvAiO&ViewerYn=Y&EdmUserId=datauser&type=S&fileName=KOydtOyKiOyZgOuFvOygkCAyMDA37Zi4LTIwMjIxMDI1Keu2ge2VnCDtlbXrrLTroKUg67KV7KCc7ZmU7J2YIO2VqOydmOyZgCDrjIDsnZEg67Cp7ZalLnBkZg%3D%3D */
    @field:Element(name = "VIEWERURL", required = false)
    var viewerUrl: String? = null,
    /** 북한 핵무력 법제화의 함의와 대응 방향 */
    @field:Element(name = "BOOKNM", required = false)
    var bookNm: String? = null,
    /** 2022-10-25 */
    @field:Element(name = "INSERTDT", required = false)
    var insertDt: String? = null,
)