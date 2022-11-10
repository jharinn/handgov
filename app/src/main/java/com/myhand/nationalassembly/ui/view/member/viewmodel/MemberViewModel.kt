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
import com.myhand.nationalassembly.data.remote.bill.model.BillItem
import com.myhand.nationalassembly.data.repository.MemberRepository
import com.myhand.nationalassembly.ui.view.member.adapter.MemberInfoItem
import com.myhand.nationalassembly.util.LogUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

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
                .collect {
                    _fetchMemberResult.value = it
                    hideLoading()
                }

            LogUtil.d("fetchMemberPaging name: $name, partyName: $partyName,origName: $origName")
        }
    }

    fun getDBMemberPhotoCount() {
        viewModelScope.launch(Dispatchers.IO) {

            withContext(Dispatchers.IO) {
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
    }

    fun getDBMemberPhotoData() {
        viewModelScope.launch(Dispatchers.IO) {
            LogUtil.d("getDBMemberPhotoData :: showLoading")
            showLoading()
            withContext(Dispatchers.IO) {
                // DB에서 사진정보 가져오기
                _memberPhotoDBData.postValue(
                    memberRepository.getDBMemberPhotoData()
                )
                LogUtil.d("DB에서 사진정보 가져오기")
                LogUtil.d("getDBMemberPhotoData :: hideLoading")
                hideLoading()
            }
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
                //성공 시 Room에 저장
                withContext(Dispatchers.IO) {
                    memberRepository.insertAllMemberPhoto(
                        *body.body.items.item.toModel().toTypedArray()
                    )
                    LogUtil.d("성공 시 Room에 저장")
                }

                withContext(Dispatchers.IO) {
                    // Preference에 사진 수 저장
                    memberRepository.savePreferencePhotoCount(response.body()?.body?.totalCount!!)
                    LogUtil.d("Preference에 사진 수 저장")
                }

                withContext(Dispatchers.IO) {
                    // DB에서 사진정보 가져오기
                    _memberPhotoDBData.postValue(
                        memberRepository.getDBMemberPhotoData()
                    )
                    LogUtil.d("DB에서 사진정보 가져오기")
                    LogUtil.d("fetchMemberPhotoData :: hideLoading")
                    hideLoading()
                }
            }
        } else {
            //api error
            LogUtil.e("member photo data error")
            LogUtil.d("fetchMemberPhotoData :: hideLoading")
            hideLoading()
        }
    }

    fun fetchMemberBill(name: String, hjName: String) = viewModelScope.launch(Dispatchers.IO) {
        val response = memberRepository.fetchMemberBill(
            name, hjName
        )
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _fetchMemberBillResult.postValue(body.body.items.item ?: listOf())
            }
        }
    }

    suspend fun showLoading() {
        withContext(Dispatchers.Main) {
            LogUtil.d("showLoading")
            isLoading.value = true

            return@withContext
        }
    }

    suspend fun hideLoading() {
        withContext(Dispatchers.Main) {
            LogUtil.d("hideLoading")
            isLoading.value = false

            return@withContext
        }
    }
}