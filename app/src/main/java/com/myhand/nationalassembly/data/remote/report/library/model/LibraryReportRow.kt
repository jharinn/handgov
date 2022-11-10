package com.myhand.nationalassembly.data.remote.report.library.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "row", strict = false)
data class LibraryReportRow @JvmOverloads constructor(
    /** 디지털 전환시대 디지털정보도서관의 미래지향적 공간 및 서비스 운영 방안 */
    @field:Element(name = "TITLE", required = false)
    var title: String? = null,
    /** https://www.nanet.go.kr/attachfiles/report/1664933327094.pdf */
    @field:Element(name = "SYSATTACH1", required = false)
    var sysattach1: String? = null,
    /** 2022-10-05 10:28:00.0 */
    @field:Element(name = "REG_DATE", required = false)
    var regDate: String? = null,
)
