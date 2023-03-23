package com.myhand.nationalassembly.data.repository

import androidx.paging.PagingData
import com.myhand.nationalassembly.data.remote.bill.BillRemoteDataSource
import com.myhand.nationalassembly.data.remote.bill.openapi.model.BillLinkRow
import com.myhand.nationalassembly.data.remote.bill.publicapi.model.BillItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BillRepositoryImpl @Inject constructor(
    private val remoteDataSource: BillRemoteDataSource
) : BillRepository {
    override fun searchBillPaging(
        numOfRows: Int?,
        pageNo: Int?,
        memName: String?,
        startOrd: String?,
        endOrd: String?,
        billKindCd: String?,
        bProcResultCd: String?,
        billName: String?,
    ): Flow<PagingData<BillItem>> {
        return remoteDataSource.searchBillPaging(
            numOfRows = numOfRows,
            pageNo = pageNo,
            memName = memName,
            startOrd = startOrd,
            endOrd = endOrd,
            billKindCd = billKindCd,
            bProcResultCd = bProcResultCd,
            billName = billName,
        )
    }

    override fun searchBillLinkPaging(
        serviceKey: String,
        pSize: Int?,
        pIndex: Int?,
        billId: String?,
        billNo: String?,
        billName: String?,
        committee: String?,
        procResult: String?,
        age: String?,
        proposer: String?
    ): Flow<PagingData<BillLinkRow>> =
        remoteDataSource.searchBillLinkPaging(
            serviceKey,
            pSize,
            pIndex,
            billId,
            billNo,
            billName,
            committee,
            procResult,
            age,
            proposer,
        )
}