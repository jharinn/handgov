package com.myhand.nationalassembly.data.remote.report.nars.model.policyresearch

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.myhand.nationalassembly.data.local.member.db.toItem
import com.myhand.nationalassembly.data.remote.base.ResultCodeOpenApi
import com.myhand.nationalassembly.ui.view.report.adapter.ReportItem
import com.myhand.nationalassembly.util.Const.PAGING_SIZE
import com.myhand.nationalassembly.util.LogUtil
import retrofit2.HttpException
import java.io.IOException

class NarsPolicyReportPagingSource(
    private val api: NarsPolicyReportApi,
    private val numOfRows: Int? = 10,
    private val pageNo: Int?,
) : PagingSource<Int, ReportItem>() {

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, ReportItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReportItem> {
        return try {
            val pageNumber = params.key ?: STARTING_PAGE_INDEX

            val response = api.fetchPolicyReport(
                pSize = params.loadSize,
                pIndex = pageNumber,
            )

            // 마지막 페이지 여부
            val totalCount = response.body()?.head?.listTotalCount
            val numOfRows = params.loadSize
            var isLast = numOfRows.times(pageNumber) > (totalCount ?: 10)

            // 다음 키 얻기
            val prevKey = if (pageNumber == STARTING_PAGE_INDEX) null else pageNumber - 1
            val nextKey = if (isLast) {
                null
            } else {
                pageNumber + (params.loadSize / PAGING_SIZE)
            }

            // 데이터 없는 경우
            if (response.body()?.code == ResultCodeOpenApi.INFO_200.resultCode) {
                LogUtil.d("데이터 없음: response.body()?.code ${response.body()?.code}")

                LoadResult.Page(
                    data = listOf<ReportItem>(),
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                // 정상 응답
                val resultCode = response.body()?.head?.result?.code
                if (resultCode != ResultCodeOpenApi.INFO_000.resultCode) {
                    throw IOException()
                }

                val data = response.body()?.row?.toItem()!!

                LoadResult.Page(
                    data = data,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }


}