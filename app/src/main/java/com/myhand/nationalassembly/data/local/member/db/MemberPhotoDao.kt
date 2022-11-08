package com.myhand.nationalassembly.data.local.member.db

import androidx.room.*
import com.myhand.nationalassembly.data.local.member.model.MemberPhotoModel
import com.myhand.nationalassembly.util.Const

@Dao
interface MemberPhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMemberPhoto(vararg memberPhotos: MemberPhotoModel)

    @Query("SELECT COUNT(num) FROM ${Const.DATABASE_MEMBER_PHOTO}")
    fun getMemberPhotoCount(): Int

    @Query("SELECT * FROM ${Const.DATABASE_MEMBER_PHOTO}")
    fun getMemberPhotoList(): List<MemberPhotoModel>
}