package com.myhand.nationalassembly.data.remote.member

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.myhand.nationalassembly.data.remote.member.model.info.MemberInfoApi
import com.myhand.nationalassembly.data.remote.member.model.info.MemberSearchPagingSource
import com.myhand.nationalassembly.data.remote.member.model.photo.MemberPhotoApi
import com.myhand.nationalassembly.data.remote.member.model.photo.MemberPhotoResponse
import com.myhand.nationalassembly.ui.view.member.adapter.MemberInfoItem
import com.myhand.nationalassembly.util.Const
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemberRemoteDataSource @Inject constructor(
    private val memberInfoApi: MemberInfoApi,
    private val memberPhotoApi: MemberPhotoApi
) {
    fun searchMemberPaging(
        numOfRows: Int? = 10,
        pageNo: Int? = 1,
        name: String?,
        partyName: String?,
        origName: String?,
    ): Flow<PagingData<MemberInfoItem>> {
        val pagingSourceFactory = {
            MemberSearchPagingSource(
                memberInfoApi,
                numOfRows,
                pageNo,
                name,
                partyName,
                origName
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

    suspend fun fetchMemberPhotoData(): Response<MemberPhotoResponse> =
        memberPhotoApi.getAllMemberPhoto()
}