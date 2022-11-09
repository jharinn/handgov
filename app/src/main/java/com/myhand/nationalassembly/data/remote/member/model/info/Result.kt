package com.myhand.nationalassembly.data.remote.member.model.info

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "RESULT")
data class Result(
    @PropertyElement(name = "CODE")
    val code: String?,
    @PropertyElement(name = "MESSAGE")
    val message: String?,
)