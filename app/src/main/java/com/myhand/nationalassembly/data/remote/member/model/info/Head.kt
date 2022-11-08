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

@Xml(name = "RESULT")
data class Result(
    @PropertyElement(name = "CODE")
    val code: String?,
    @PropertyElement(name = "MESSAGE")
    val message: String?,
)