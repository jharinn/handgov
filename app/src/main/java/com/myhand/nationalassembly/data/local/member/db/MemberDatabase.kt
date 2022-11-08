package com.myhand.nationalassembly.data.local.member.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.myhand.nationalassembly.data.local.member.model.MemberPhotoModel
import com.myhand.nationalassembly.util.Const

@Database(
    entities = [MemberPhotoModel::class],
    version = 1,
    exportSchema = false
)
abstract class MemberPhotoDatabase : RoomDatabase() {
    abstract fun memberPhotoDao(): MemberPhotoDao

    companion object {
        @Volatile
        private var INSTANCE: MemberPhotoDatabase? = null
        private fun buildDatabase(context: Context): MemberPhotoDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                MemberPhotoDatabase::class.java,
                Const.DATABASE_MEMBER_PHOTO
            ).build()

        fun getInstance(context: Context): MemberPhotoDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
    }


}