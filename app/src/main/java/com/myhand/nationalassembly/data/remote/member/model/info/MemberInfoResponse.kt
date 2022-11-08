package com.myhand.nationalassembly.data.remote.member.model.info

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "nwvrqwxyaytdsfvhu")
data class MemberInfoResponse(
    @Element(name = "head")
    val head: Head,
    @Element(name = "row")
    val row: List<MemberInfoRow>,
)