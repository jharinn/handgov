package com.myhand.nationalassembly.data.remote.base

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "head", strict = false)
data class Head @JvmOverloads constructor(
    @field: Element(name = "list_total_count", required = false)
    var listTotalCount: Int? = null,
    @field:Element(name = "RESULT", required = false)
    var result: Result? = null,
)