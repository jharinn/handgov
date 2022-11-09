package com.myhand.nationalassembly.data.repository

import androidx.paging.PagingData
import com.myhand.nationalassembly.data.local.member.MemberLocalDataSource
import com.myhand.nationalassembly.data.local.member.model.MemberPhotoModel
import com.myhand.nationalassembly.data.remote.member.MemberRemoteDataSource
import com.myhand.nationalassembly.data.remote.member.model.photo.MemberPhotoResponse
import com.myhand.nationalassembly.ui.view.member.adapter.MemberInfoItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemberRepositoryImpl @Inject constructor(
    private val localDataSource: MemberLocalDataSource,
    private val remoteDataSource: MemberRemoteDataSource
) : MemberRepository {

    override fun searchMember(
        numOfRows: Int?,
        pageNo: Int?,
        name: String?,
        partyName: String?,
        origName: String?,
    ): Flow<PagingData<MemberInfoItem>> {
        return remoteDataSource.searchMemberPaging(
            numOfRows,
            pageNo,
            name,
            partyName,
            origName,
        )
    }

    override suspend fun insertAllMemberPhoto(vararg memberPhotos: MemberPhotoModel) {
        localDataSource.insertAllMemberPhoto(*memberPhotos)
    }

    override suspend fun fetchMemberPhotoData(): Response<MemberPhotoResponse> {
        return remoteDataSource.fetchMemberPhotoData()
    }

    override fun getDBMemberPhotoCount(): Int {
        return localDataSource.getMemberPhotoCount()
    }

    override fun getDBMemberPhotoData(): List<MemberPhotoModel> {
        return localDataSource.getMemberPhotoList()
    }

    override suspend fun savePreferencePhotoCount(count: Int) {
        return localDataSource.savePhotoCount(count)
    }

    override suspend fun getPreferencePhotoCount(): Flow<Int> {
        return localDataSource.getPhotoCount()
    }
}