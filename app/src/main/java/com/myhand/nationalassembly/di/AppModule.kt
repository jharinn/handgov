package com.myhand.nationalassembly.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.myhand.nationalassembly.data.local.member.db.MemberPhotoDatabase
import com.myhand.nationalassembly.data.remote.bill.openapi.BillLinkApi
import com.myhand.nationalassembly.data.remote.bill.publicapi.BillApi
import com.myhand.nationalassembly.data.remote.member.info.MemberInfoApi
import com.myhand.nationalassembly.data.remote.member.photo.MemberPhotoApi
import com.myhand.nationalassembly.data.remote.report.library.LibraryReportApi
import com.myhand.nationalassembly.data.remote.report.nars.model.global.NarsGlobalReportApi
import com.myhand.nationalassembly.data.remote.report.nars.model.issue.NarsIssueReportApi
import com.myhand.nationalassembly.data.remote.report.nars.model.policyresearch.NarsPolicyReportApi
import com.myhand.nationalassembly.data.remote.schedule.meeting.MeetingScheduleApi
import com.myhand.nationalassembly.data.remote.schedule.seminar.SeminarScheduleApi
import com.myhand.nationalassembly.util.Const
import com.myhand.nationalassembly.util.Const.DATASTORE_NAME
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.converter.htmlescape.HtmlEscapeStringConverter
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
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
                    TikXml.Builder()
                        .addTypeConverter(String::class.java, HtmlEscapeStringConverter())
                        .exceptionOnUnreadXml(false)
                        .build()
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
            .addConverterFactory(SimpleXmlConverterFactory.create())
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

    @Singleton
    @Provides
    fun provideBillLinkApiService(@Named("OpenApi") retrofit: Retrofit): BillLinkApi {
        return retrofit.create(BillLinkApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSeminarScheduleApiService(@Named("OpenApi") retrofit: Retrofit): SeminarScheduleApi {

        return retrofit.create(SeminarScheduleApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMeetingScheduleApiService(@Named("OpenApi") retrofit: Retrofit): MeetingScheduleApi {
        return retrofit.create(MeetingScheduleApi::class.java)
    }

    @Singleton
    @Provides
    fun provideLibraryReportApiService(@Named("OpenApi") retrofit: Retrofit): LibraryReportApi {
        return retrofit.create(LibraryReportApi::class.java)
    }

    @Singleton
    @Provides
    fun provideIssueReportApiService(@Named("OpenApi") retrofit: Retrofit): NarsIssueReportApi {
        return retrofit.create(NarsIssueReportApi::class.java)
    }

    @Singleton
    @Provides
    fun provideGlobalReportApiService(@Named("OpenApi") retrofit: Retrofit): NarsGlobalReportApi {
        return retrofit.create(NarsGlobalReportApi::class.java)
    }

    @Singleton
    @Provides
    fun providePolicyReportApiService(@Named("OpenApi") retrofit: Retrofit): NarsPolicyReportApi {
        return retrofit.create(NarsPolicyReportApi::class.java)
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