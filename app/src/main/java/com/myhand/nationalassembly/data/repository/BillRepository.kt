package com.myhand.nationalassembly.data.repository

import androidx.paging.PagingData
import com.myhand.nationalassembly.data.remote.bill.openapi.model.BillLinkRow
import com.myhand.nationalassembly.data.remote.bill.publicapi.model.BillItem
import com.myhand.nationalassembly.util.Const
import kotlinx.coroutines.flow.Flow

interface BillRepository {
    fun searchBillPaging(
        numOfRows: Int?,
        pageNo: Int?,
        memName: String?,
        startOrd: String?,
        endOrd: String?,
        billKindCd: String?,
        bProcResultCd: String?,
        billName: String?,
    ): Flow<PagingData<BillItem>>

    fun searchBillLinkPaging(
        serviceKey: String = Const.OPEN_API_KEY,
        pSize: Int? = 10,
        pIndex: Int? = 1,
        billId: String?,
        billNo: String?,
        billName: String?,
        committee: String?,
        procResult: String?,
        age: String?,
        proposer: String?,
    ): Flow<PagingData<BillLinkRow>>
}