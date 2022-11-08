package com.myhand.nationalassembly.data.remote.base

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "header")
data class Header(
    @PropertyElement
    val resultCode: String,
    @PropertyElement
    val resultMsg: String,
)

