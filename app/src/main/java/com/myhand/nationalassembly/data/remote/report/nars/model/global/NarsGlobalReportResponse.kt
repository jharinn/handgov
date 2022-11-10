package com.myhand.nationalassembly.data.remote.report.nars.model.global

import com.myhand.nationalassembly.data.remote.base.Head
import com.myhand.nationalassembly.data.remote.report.nars.model.base.NarsReportRow
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "nhtegpibasggyssce", strict = true)
data class NarsGlobalReportResponse @JvmOverloads constructor(
    @field:Element(name = "head", required = false)
    var head: Head? = null,
    @field:ElementList(name = "row", inline = true, required = false)
    var row: List<NarsReportRow>? = null,
    @field:Element(name = "CODE", required = false)
    var code: String? = null,
    @field:Element(name = "MESSAGE", required = false)
    var message: String? = null,
)