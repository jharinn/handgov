package com.myhand.nationalassembly.data.local.member

import com.myhand.nationalassembly.data.local.member.db.MemberPhotoDatabase
import com.myhand.nationalassembly.data.local.member.model.MemberPhotoModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemberLocalDataSource @Inject constructor(val photoDB: MemberPhotoDatabase) {

    suspend fun insertAllMemberPhoto(vararg memberPhotos: MemberPhotoModel) {
        photoDB.memberPhotoDao().insertAllMemberPhoto(*memberPhotos)
    }

    fun getMemberPhotoCount(): Int {
        return photoDB.memberPhotoDao().getMemberPhotoCount()
    }

    fun getMemberPhotoList(): List<MemberPhotoModel> {
        return photoDB.memberPhotoDao().getMemberPhotoList()
    }
}