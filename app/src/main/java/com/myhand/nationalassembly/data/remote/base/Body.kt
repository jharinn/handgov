package com.myhand.nationalassembly.data.remote.base

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement

open class Body<T>(
    @Element(name = "items")
    open val items: T,
    @PropertyElement
    open val numOfRows: Int?,
    @PropertyElement
    open val pageNo: Int?,
    @PropertyElement
    open val totalCount: Int?,
)
