package com.myhand.nationalassembly.data.remote.bill.model

import com.myhand.nationalassembly.data.remote.base.Header
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "response")
data class BillResponse(
    @Element
    val header: Header,
    @Element(name = "body")
    val body: BillBody,
)