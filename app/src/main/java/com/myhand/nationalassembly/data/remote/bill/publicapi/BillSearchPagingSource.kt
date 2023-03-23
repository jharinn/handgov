package com.myhand.nationalassembly.data.remote.bill.publicapi

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.myhand.nationalassembly.data.remote.base.ResultCodePublicData
import com.myhand.nationalassembly.data.remote.bill.publicapi.model.BillItem
import com.myhand.nationalassembly.util.Const
import com.myhand.nationalassembly.util.Const.PAGING_SIZE
import retrofit2.HttpException
import java.io.IOException

class BillSearchPagingSource(
    private val api: BillApi,
    serviceKey: String = Const.BILL_API_KEY,
    numOfRows: Int?,
    pageNo: Int?,
    memName: String?,
    startOrd: String?, //시작대수
    endOrd: String?, //마지막대수
    billKindCd: String?, //의안종류
    bProcResultCd: String?, //본회의처리결과
    billName: String?, //의안명
    gbn: String? = "dae_num_name", //구분
) : PagingSource<Int, BillItem>() {

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, BillItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BillItem> {
        return try {
            val pageNumber = params.key ?: STARTING_PAGE_INDEX
            val response = api.searchBill(numOfRows = params.loadSize, pageNo = params.key)

            // 마지막 페이지 여부
            val totalCount = response.body()?.body?.totalCount
            val numOfRows = response.body()?.body?.numOfRows
            val pageNo = response.body()?.body?.pageNo
            var isLast = (numOfRows?.times(pageNo ?: 1) ?: 1) > (totalCount ?: 10)

            val resultCode = response.body()?.header?.resultCode

            if (resultCode != ResultCodePublicData.NORMAL.resultCode) {
                throw IOException()
            }
            val data = response.body()?.body?.items?.item!!
            val prevKey = if (pageNumber == STARTING_PAGE_INDEX) null else pageNumber - 1
            val nextKey = if (isLast) {
                null
            } else {
                pageNumber + (params.loadSize / PAGING_SIZE)
            }

            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}