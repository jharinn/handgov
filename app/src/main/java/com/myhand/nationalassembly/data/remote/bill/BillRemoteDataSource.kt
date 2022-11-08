package com.myhand.nationalassembly.data.remote.bill

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.myhand.nationalassembly.data.remote.bill.model.BillItem
import com.myhand.nationalassembly.util.Const
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BillRemoteDataSource @Inject constructor(
    private val billApi: BillApi
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
}