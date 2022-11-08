package com.myhand.nationalassembly.data.remote.member.model.photo

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "Items")
data class MemberPhotoItems(
    @Element(name = "item")
    val item: List<MemberPhotoItem>
)