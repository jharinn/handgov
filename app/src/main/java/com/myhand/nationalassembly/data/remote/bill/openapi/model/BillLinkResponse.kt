package com.myhand.nationalassembly.data.remote.bill.openapi.model

import com.myhand.nationalassembly.data.remote.base.Head
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "nzmimeepazxkubdpn", strict = true)
data class BillLinkResponse @JvmOverloads constructor(
    @field:Element(name = "head", required = false)
    var head: Head? = null,
    @field:ElementList(name = "row", inline = true, required = false)
    var row: List<BillLinkRow>? = null,
    @field:Element(name = "CODE", required = false)
    var code: String? = null,
    @field:Element(name = "MESSAGE", required = false)
    var message: String? = null,
)