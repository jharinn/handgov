package com.myhand.nationalassembly.data.remote.bill.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "Items")
data class BillItems(
    @Element(name = "item")
    val item: List<BillItem>? = listOf()
)