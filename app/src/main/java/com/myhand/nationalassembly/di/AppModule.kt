package com.myhand.nationalassembly.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.myhand.nationalassembly.data.local.member.db.MemberPhotoDatabase
import com.myhand.nationalassembly.data.remote.bill.BillApi
import com.myhand.nationalassembly.data.remote.member.model.info.MemberInfoApi
import com.myhand.nationalassembly.data.remote.member.model.photo.MemberPhotoApi
import com.myhand.nationalassembly.util.Const
import com.myhand.nationalassembly.util.Const.DATASTORE_NAME
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //Retrofit
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    @Named("PublicDataSecretariat")
    fun provideSecretariatXmlRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(
                TikXmlConverterFactory.create(
                    TikXml.Builder().exceptionOnUnreadXml(false).build()
                )
            )
            .client(okHttpClient)
            .baseUrl(Const.NA_Secretariat_API_BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    @Named("OpenApi")
    fun provideOpenApiXmlRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(
                TikXmlConverterFactory.create(
                    TikXml.Builder().exceptionOnUnreadXml(false).build()
                )
            )
            .client(okHttpClient)
            .baseUrl(Const.OPEN_API_BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideMemberPhotoApiService(@Named("PublicDataSecretariat") retrofit: Retrofit): MemberPhotoApi {
        return retrofit.create(MemberPhotoApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMemberInfoApiService(@Named("OpenApi") retrofit: Retrofit): MemberInfoApi {
        return retrofit.create(MemberInfoApi::class.java)
    }

    @Singleton
    @Provides
    fun provideBillApiService(@Named("PublicDataSecretariat") retrofit: Retrofit): BillApi {
        return retrofit.create(BillApi::class.java)
    }

    //Room
    @Singleton
    @Provides
    fun provideMemberPhotoDatabase(@ApplicationContext context: Context): MemberPhotoDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            MemberPhotoDatabase::class.java,
            "member_photo"
        ).build()

    //DataStore
    @Singleton
    @Provides
    fun providePreferenceDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile((DATASTORE_NAME)) }
        )
//
//    // WorkManager
//    @Singleton
//    @Provides
//    fun provideWorkManager(@ApplicationContext context: Context): WorkManager =
//        WorkManager.getInstance(context)
//
//    @Singleton
//    @Provides
//    fun provideCacheDeleteResult(): String = "Cache has deleted by Hilt"
}