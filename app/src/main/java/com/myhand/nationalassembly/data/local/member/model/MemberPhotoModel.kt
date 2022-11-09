package com.myhand.nationalassembly.data.local.member.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myhand.nationalassembly.util.Const
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Const.DATABASE_MEMBER_PHOTO)
data class MemberPhotoModel(
    /** 강기윤 */
    var empNm: String,
    /** 姜起潤 */
    var hjNm: String,
    /** http://www.assembly.go.kr/photo/9771230.jpg */
    var jpgLink: String,
    /** 2685 */
    @PrimaryKey(autoGenerate = false)
    var num: String,
    /** 경남 창원시성산구 */
    var origNm: String,
) : Parcelable
