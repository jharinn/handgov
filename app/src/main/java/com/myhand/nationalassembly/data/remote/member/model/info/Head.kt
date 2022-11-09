package com.myhand.nationalassembly.data.remote.member.model.info

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "head")
data class Head(
    @PropertyElement(name = "list_total_count")
    val listTotalCount: Int?,
    @Element(name = "RESULT")
    val result: Result,
)