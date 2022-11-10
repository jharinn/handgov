package com.myhand.nationalassembly.data.remote.bill

import com.myhand.nationalassembly.data.remote.bill.model.BillResponse
import com.myhand.nationalassembly.util.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BillApi {
    @GET("BillInfoService2/getBillInfoList")
    suspend fun searchBill(
        @Query("ServiceKey") serviceKey: String = Const.BILL_API_KEY,
        @Query("numOfRows") numOfRows: Int? = null,
        @Query("pageNo") pageNo: Int? = null,
        /**
         * 발의자
         * 국회의원 성명
         */
        @Query("mem_name") memName: String? = null,
        /**
         * 발의자 한자이름
         * 동명이인 구분용
         */
        @Query("hj_nm") hjName: String? = null,
        @Query("start_ord") startOrd: String? = null, //시작대수
        @Query("end_ord") endOrd: String? = null, //마지막대수
        /**
         * 의안종류
        1.헌법개정 B01
        2.예산안 B02
        3.결산 B03
        4.법률안 B04
        5.동의안 B05
        6.승인안 B06
        7.결의안 B07
        8.건의안 B08
        9.규칙안 B09
        10.선출안 B10
        11.중요동의 B11
        12.의원징계 B12
        13.의원자격심사 B13
        14.윤리심사 B14
        15.기타안 B15
        16.기타B16
         */
        @Query("bill_kind_cd") billKindCd: String? = null, //의안종류
        @Query("b_proc_result_cd") bProcResultCd: String? = null, //본회의처리결과
        @Query("bill_name") billName: String? = null, //의안명
        /**
         * 구분
        1.제안대수검색
        1)이름포함gbn=dae_num_name
        2)이름미포함
        gbn=dae_num
        2.본회의처리회기로 검색
        1)이름포함
        gbn=process_num_name
        2)이름미포함
        gbn=process_num
        3.제안회기로 검색
        1)이름포함
        gbn=propose_num_name
        2)이름미포함
        gbn=propose_num

        각 파라미터들 중복 사용 불가
         */
        @Query("gbn") gbn: String? = "dae_num_name", //구분
    ): Response<BillResponse>
}