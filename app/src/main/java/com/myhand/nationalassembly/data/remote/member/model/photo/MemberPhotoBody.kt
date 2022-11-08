package com.myhand.nationalassembly.data.remote.member.model.photo

import com.myhand.nationalassembly.data.remote.base.Body
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "body")
data class MemberPhotoBody(
    @Element(name = "items")
    override val items: MemberPhotoItems,
    @PropertyElement
    override val numOfRows: Int?,
    @PropertyElement
    override val pageNo: Int?,
    @PropertyElement
    override val totalCount: Int?
) : Body<MemberPhotoItems>(
    items, numOfRows, pageNo, totalCount
)