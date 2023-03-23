package com.myhand.nationalassembly.data.remote.bill.publicapi.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "body")
data class BillBody(
    @Element(name = "items")
    val items: BillItems,
    @PropertyElement
    val numOfRows: Int?,
    @PropertyElement
    val pageNo: Int?,
    @PropertyElement
    val totalCount: Int?,
)