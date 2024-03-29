package com.myhand.nationalassembly.data.repository

import androidx.paging.PagingData
import com.myhand.nationalassembly.data.local.member.model.MemberPhotoModel
import com.myhand.nationalassembly.data.remote.bill.publicapi.model.BillResponse
import com.myhand.nationalassembly.data.remote.member.photo.model.MemberPhotoResponse
import com.myhand.nationalassembly.ui.view.member.adapter.MemberInfoItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MemberRepository {

    fun searchMember(
        numOfRows: Int? = 10,
        pageNo: Int? = 1,
        name: String?,
        partyName: String?,
        origName: String?,
    ): Flow<PagingData<MemberInfoItem>>

    suspend fun insertAllMemberPhoto(vararg item: MemberPhotoModel)

    suspend fun fetchMemberPhotoData(): Response<MemberPhotoResponse>

    suspend fun fetchMemberBill(
        mName: String?,
        mhjName: String?,
        memNameCheck: String?,
        startOrd: Int?,
        endOrd: Int?
    ): Response<BillResponse>

    fun getDBMemberPhotoCount(): Int

    fun getDBMemberPhotoData(): List<MemberPhotoModel>

    suspend fun savePreferencePhotoCount(mode: Int)

    suspend fun getPreferencePhotoCount(): Flow<Int>


//    //Room
//    suspend fun insertBooks(book: Book)
//
//    suspend fun deleteBooks(book: Book)
//
//    fun getFavoriteBooks(): Flow<List<Book>>
//
//    //DataStore
//    suspend fun saveSortMode(mode: String)
//    suspend fun getSortMode(): Flow<String>
//    suspend fun saveCacheDeleteMode(mode: Boolean)
//    suspend fun getCacheDeleteMode(): Flow<Boolean>
//
//    // Paging
//    fun getFavoritePagingBooks(): Flow<PagingData<Book>>

}
