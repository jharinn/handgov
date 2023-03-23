package com.myhand.nationalassembly.data.remote.bill.openapi.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "row", strict = false)
data class BillLinkRow @JvmOverloads constructor(
    /** 의안ID PRC_T2Z2F1Y1X0W8Z1H5D4M6U0V7F3I9E7 */
    @field:Element(name = "BILL_ID", required = false)
    var billId: String? = null,
    /** 의안번호 2118204 */
    @field:Element(name = "BILL_NO", required = false)
    var billNo: String? = null,
    /** 법률안명 가상자산 불공정거래 규제 등에 관한 법률안 */
    @field:Element(name = "BILL_NAME", required = false)
    var billName: String? = null,
    /** 제안일 2022-11-10 */
    @field:Element(name = "PROPOSE_DT", required = false)
    var propose_dt: String? = null,
    /** 처리상태 임기만료폐기 *nullable */
    @field:Element(name = "PROC_RESULT", required = false)
    var procResult: String? = null,
    /** 대수 21*/
    @field:Element(name = "AGE", required = false)
    var age: String? = null,
    /** 상세페이지 http://likms.assembly.go.kr/bill/billDetail.do?billId=PRC_T2Z2F1Y1X0W8Z1H5D4M6U0V7F3I9E7&ageFrom=21&ageTo=21*/
    @field:Element(name = "DETAIL_LINK", required = false)
    var detailLink: String? = null,
    /** 제안자 백혜련의원 등 10인 */
    @field:Element(name = "PROPOSER", required = false)
    var proposer: String? = null,
    /** 대표발의자 백혜련 */
    @field:Element(name = "RST_PROPOSER", required = false)
    var rstProposer: String? = null,
    /** 공동발의자 김영진,김철민,박상혁,박재호,송기헌,윤관석,임호선,전용기,최종윤*/
    @field:Element(name = "PUBL_PROPOSER", required = false)
    var publProposer: String? = null,
)