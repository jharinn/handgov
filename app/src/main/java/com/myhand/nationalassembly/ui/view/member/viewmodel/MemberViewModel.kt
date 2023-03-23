package com.myhand.nationalassembly.ui.view.member.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.myhand.nationalassembly.data.local.member.db.toModel
import com.myhand.nationalassembly.data.local.member.model.MemberPhotoModel
import com.myhand.nationalassembly.data.remote.bill.publicapi.model.BillItem
import com.myhand.nationalassembly.data.repository.MemberRepository
import com.myhand.nationalassembly.ui.view.member.adapter.MemberInfoItem
import com.myhand.nationalassembly.util.LogUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class MemberViewModel @Inject constructor(
    private val memberRepository: MemberRepository
) : ViewModel() {

    private val _fetchMemberResult =
        MutableStateFlow<PagingData<MemberInfoItem>>(PagingData.empty())
    val fetchMemberResult: StateFlow<PagingData<MemberInfoItem>> = _fetchMemberResult.asStateFlow()

    // 국회의원 사진 from DB
    private val _memberPhotoDBData: MutableLiveData<List<MemberPhotoModel>> =
        MutableLiveData(mutableListOf<MemberPhotoModel>())
    val memberPhotoDBData: LiveData<List<MemberPhotoModel>> = _memberPhotoDBData

    // 국회의원 DB count
    var memberDBCount: MutableLiveData<Int> = MutableLiveData<Int>()

    // 국회의원 발의법안
    val _fetchMemberBillResult = MutableLiveData<List<BillItem>>()
    val fetchMemberBillResult: LiveData<List<BillItem>> get() = _fetchMemberBillResult

    val isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isEmpty: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    fun fetchMemberPaging(
        numOfRows: Int? = 10,
        pageNo: Int? = 1,
        name: String?,
        partyName: String?,
        origName: String?,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            showLoading()

            memberRepository.searchMember(
                numOfRows,
                pageNo,
                name,
                partyName,
                origName,
            )
                .cachedIn(viewModelScope)
                .map {
                    it.map { member ->
                        for (photo in memberPhotoDBData.value!!) {
                            if (member.name == photo.empNm && member.oriLocalName == photo.origNm) {
                                member.photoLink = photo.jpgLink
                            }
                        }
                        member
                    }
                }
                .catch { e ->
                    LogUtil.d("Error fetching member data: ${e.message}")
                    hideLoading()
                }
                .flowOn(Dispatchers.IO)
                .onEmpty {
                    LogUtil.d("EmptyData!!")
                    isEmpty.value = true
                }
                .flowOn(Dispatchers.Main)
                .collect {
                    _fetchMemberResult.value = it
                    hideLoading()
                }
        }
    }

    fun getDBMemberPhotoCount() {
        viewModelScope.launch(Dispatchers.IO) {
            val dbCount = memberRepository.getDBMemberPhotoCount()
            val prefCount = getPreferenceMemberPhotoCount()

            // DB에 저장된 사진정보 사이즈 > 0
            // Pref에 저장된 사진정보 사이즈 > 0
            // DB에 저장된 사진 수 == Pref에 저장된 수
            if (dbCount > 0 && prefCount > 0 && dbCount == prefCount) //DB에서 사진정보 가져오기
                getDBMemberPhotoData()
            else // API실행
                fetchMemberPhotoData()
        }
    }

    fun getDBMemberPhotoData() {
        viewModelScope.launch(Dispatchers.IO) {
            LogUtil.d("getDBMemberPhotoData :: showLoading")
            showLoading()

            // DB에서 사진정보 가져오기
            _memberPhotoDBData.postValue(
                memberRepository.getDBMemberPhotoData()
            )
            LogUtil.d("DB에서 사진정보 가져오기")

            hideLoading()
            LogUtil.d("getDBMemberPhotoData :: hideLoading")
        }
    }

    // Preference 에 저장된 사진정보 갯수
    suspend fun getPreferenceMemberPhotoCount(): Int {
        return memberRepository.getPreferencePhotoCount().first()
    }

    fun fetchMemberPhotoData() = viewModelScope.launch(Dispatchers.IO) {
        LogUtil.d("fetchMemberPhotoData :: showLoading")
        showLoading()
        // public data에서 사진 데이터 가져오기
        val response = memberRepository.fetchMemberPhotoData()

        LogUtil.d("api 통신 isSuccessful? = ${response.isSuccessful}")
        if (response.isSuccessful) {
            response.body()?.let { body ->
                // 1. Save photo data in Room Database if APi call succeed.
                // Measure the time it takes to insert the data into the Room database
                val insertTime = measureTimeMillis {
                    memberRepository.insertAllMemberPhoto(
                        *body.body.items.item.toModel().toTypedArray()
                    )
                }
                LogUtil.d("Time taken to insert data into Room database: $insertTime ms")

                // 2. Save number of photos in Preference.
                memberRepository.savePreferencePhotoCount(response.body()?.body?.totalCount!!)
                LogUtil.d("Preference에 사진 수 저장")

                // 3. Retrieve photo data from Room Database.
                _memberPhotoDBData.postValue(
                    memberRepository.getDBMemberPhotoData()
                )
                LogUtil.d("DB에서 사진정보 가져오기")
            }
        } else {
            //api error
            LogUtil.e("member photo data error")
        }
        hideLoading()
    }

    /**
     * 국회의원이 발의한 법안 데이터 가져오기
     */
    fun fetchMemberBill(name: String, hjName: String) = viewModelScope.launch(Dispatchers.IO) {
        val response = memberRepository.fetchMemberBill(
            name, hjName, "G01", 21, 21
        )
        if (response.isSuccessful) {
            response.body()?.let { body ->

                // 문자열 수정
                var item = body.body.items.item
                item?.forEach {
                    it.summary = it.summary?.replace("&#xD;", "")
                }

                _fetchMemberBillResult.postValue(item ?: listOf())
            }
        }
    }

    suspend fun showLoading() {
        withContext(Dispatchers.Main) {
            isLoading.value = true
            return@withContext
        }
    }

    suspend fun hideLoading() {
        withContext(Dispatchers.Main) {
            isLoading.value = false
            return@withContext
        }
    }
}