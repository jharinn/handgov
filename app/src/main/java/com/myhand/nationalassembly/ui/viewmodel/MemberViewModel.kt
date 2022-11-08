package com.myhand.nationalassembly.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.myhand.nationalassembly.data.local.member.db.toItem
import com.myhand.nationalassembly.data.local.member.db.toModel
import com.myhand.nationalassembly.data.remote.member.model.photo.MemberPhotoItem
import com.myhand.nationalassembly.data.remote.member.model.photo.MemberPhotoResponse
import com.myhand.nationalassembly.data.repository.MemberRepository
import com.myhand.nationalassembly.ui.view.member.adapter.MemberInfoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(
    private val memberRepository: MemberRepository
) : ViewModel() {

    private val _fetchMemberResult =
        MutableStateFlow<PagingData<MemberInfoItem>>(PagingData.empty())
    val fetchMemberResult: StateFlow<PagingData<MemberInfoItem>> = _fetchMemberResult.asStateFlow()

    // 국회의원 사진 from DB
    private val _memberPhotoDBData: MutableLiveData<List<MemberPhotoItem>> =
        MutableLiveData(mutableListOf<MemberPhotoItem>())
    val memberPhotoDBData: LiveData<List<MemberPhotoItem>> = _memberPhotoDBData

    val isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    fun fetchMemberPaging(query: String) {
        viewModelScope.launch {
            memberRepository.searchMember()
                .cachedIn(viewModelScope)
                .collect {
                    _fetchMemberResult.value = it
                }
        }
    }

    // 국회의원 사진 api
    private val _memberPhotoResult = MutableLiveData<MemberPhotoResponse>()
    val memberPhotoResult: LiveData<MemberPhotoResponse> get() = _memberPhotoResult

    fun getMemberPhotoCount(): Int = memberRepository.getMemberPhotoCount()

    fun fetchMemberPhotoData() = viewModelScope.launch(Dispatchers.IO) {
        showLoading()

        val response = memberRepository.fetchMemberPhotoData()

        if (response.isSuccessful) {
            response.body()?.let { body ->
                _memberPhotoResult.value = body
            }

            hideLoading()
        } else
            hideLoading()
    }

    fun getMemberPhotoDataFromDB() {
        _memberPhotoDBData.value = memberRepository.getMemberPhotoDataFromDB().toItem()
    }

    fun saveAllMemberPhotoData() = viewModelScope.launch(Dispatchers.IO) {
        val photos = memberPhotoResult.value?.body?.items?.item?.toModel()
        memberRepository.insertAllMemberPhoto(*photos?.toTypedArray()!!)
    }

    fun showLoading() {
        viewModelScope.launch(Dispatchers.Main) {
            isLoading.value = true
        }
    }

    fun hideLoading() {
        viewModelScope.launch(Dispatchers.Main) {
            isLoading.value = false
        }
    }
}