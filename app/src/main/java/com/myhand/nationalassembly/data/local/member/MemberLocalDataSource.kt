package com.myhand.nationalassembly.data.local.member

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import com.myhand.nationalassembly.data.local.member.MemberLocalDataSource.PreferencesKeys.MEMBER_PHOTO_COUNT
import com.myhand.nationalassembly.data.local.member.db.MemberPhotoDatabase
import com.myhand.nationalassembly.data.local.member.model.MemberPhotoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemberLocalDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val photoDB: MemberPhotoDatabase
) {

    suspend fun insertAllMemberPhoto(vararg memberPhotos: MemberPhotoModel) {
        photoDB.memberPhotoDao().insertAllMemberPhoto(*memberPhotos)
    }

    fun getMemberPhotoCount(): Int {
        return photoDB.memberPhotoDao().getMemberPhotoCount()
    }

    fun getMemberPhotoList(): List<MemberPhotoModel> {
        return photoDB.memberPhotoDao().getMemberPhotoList()
    }

    //DataStroe
    private object PreferencesKeys {
        val MEMBER_PHOTO_COUNT = intPreferencesKey("MEMBER_PHOTO_COUNT")
    }

    suspend fun savePhotoCount(mode: Int) {
        dataStore.edit { prefs ->
            prefs[MEMBER_PHOTO_COUNT] = mode
        }
    }

    suspend fun getPhotoCount(): Flow<Int> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { prefs ->
                prefs[MEMBER_PHOTO_COUNT] ?: 0
            }
    }

}