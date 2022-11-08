package com.myhand.nationalassembly.data.remote.bill.model

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "Item")
data class BillItem(
    //의안ID
    @PropertyElement(name = "billId") var billId: String?,
    //의안명
    @PropertyElement(name = "billName") var billName: String?,
    //의안번호
    @PropertyElement(name = "billNo") var billNo: String?,
    //의결결과
    @PropertyElement(name = "generalResult") var generalResult: String?,
    //처리구분 - 처리의안/계류의안
    @PropertyElement(name = "passGubn") var passGubn: String?,
    //의결일자
    @PropertyElement(name = "procDt") var procDt: String?,
    //심사진행상태
    @PropertyElement(name = "procStageCd") var procStageCd: String?,
    //제안일자
    @PropertyElement(name = "proposeDt") var proposeDt: String?,
    //제안자구분
    @PropertyElement(name = "proposerKind") var proposerKind: String?,
    //주요내용
    @PropertyElement(name = "summary") var summary: String?,
)