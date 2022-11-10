package com.myhand.nationalassembly.data.remote.base

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "RESULT", strict = false)
data class Result @JvmOverloads constructor(
    @field:Element(name = "CODE", required = false)
    var code: String? = null,
    @field:Element(name = "MESSAGE", required = false)
    var message: String? = null,
)