package com.myhand.nationalassembly.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
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
    private val dataStore: DataStore<Preferences>,
    private val localDataSource: MemberLocalDataSource,
    private val remoteDataSource: MemberRemoteDataSource
) : MemberRepository {

    override fun searchMember(numOfRows: Int?, pageNo: Int?): Flow<PagingData<MemberInfoItem>> {
        return remoteDataSource.searchMemberPaging()
    }

    override suspend fun insertAllMemberPhoto(vararg memberPhotos: MemberPhotoModel) {
        localDataSource.insertAllMemberPhoto(*memberPhotos)
    }

    override suspend fun fetchMemberPhotoData(): Response<MemberPhotoResponse> {
        return remoteDataSource.fetchMemberPhotoData()
    }

    override fun getMemberPhotoCount(): Int {
        return localDataSource.getMemberPhotoCount()
    }

    override fun getMemberPhotoDataFromDB(): List<MemberPhotoModel> {
        return localDataSource.getMemberPhotoList()
    }
}