package com.myhand.nationalassembly.data.remote.bill

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.myhand.nationalassembly.data.remote.bill.openapi.BillLinkApi
import com.myhand.nationalassembly.data.remote.bill.openapi.BillLinkSearchPagingSource
import com.myhand.nationalassembly.data.remote.bill.openapi.model.BillLinkRow
import com.myhand.nationalassembly.data.remote.bill.publicapi.BillApi
import com.myhand.nationalassembly.data.remote.bill.publicapi.BillSearchPagingSource
import com.myhand.nationalassembly.data.remote.bill.publicapi.model.BillItem
import com.myhand.nationalassembly.util.Const
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BillRemoteDataSource @Inject constructor(
    private val billApi: BillApi,
    private val billLinkApi: BillLinkApi
) {

    fun searchBillPaging(
        serviceKey: String = Const.BILL_API_KEY,
        numOfRows: Int?,
        pageNo: Int?,
        memName: String?,
        startOrd: String?,
        endOrd: String?,
        billKindCd: String?,
        bProcResultCd: String?,
        billName: String?,
        gbn: String? = "dae_num_name",
    ): Flow<PagingData<BillItem>> {

        val pagingSourceFactory = {
            BillSearchPagingSource(
                billApi,
                serviceKey,
                numOfRows,
                pageNo,
                memName,
                startOrd,
                endOrd,
                billKindCd,
                bProcResultCd,
                billName,
                gbn
            )
        }

        return Pager(
            config = PagingConfig(
                pageSize = Const.PAGING_SIZE,
                enablePlaceholders = false,
                maxSize = Const.PAGING_SIZE * 3
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

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
    ): Flow<PagingData<BillLinkRow>> {

        val pagingSourceFactory = {
            BillLinkSearchPagingSource(
                billLinkApi,
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

        return Pager(
            config = PagingConfig(
                pageSize = Const.PAGING_SIZE,
                enablePlaceholders = false,
                maxSize = Const.PAGING_SIZE * 3
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}