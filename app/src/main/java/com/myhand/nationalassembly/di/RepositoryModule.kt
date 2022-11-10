package com.myhand.nationalassembly.di

import com.myhand.nationalassembly.data.repository.MemberRepository
import com.myhand.nationalassembly.data.repository.MemberRepositoryImpl
import com.myhand.nationalassembly.data.repository.ScheduleRepository
import com.myhand.nationalassembly.data.repository.ScheduleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindMemberRepository(
        memberRepositoryImpl: MemberRepositoryImpl
    ): MemberRepository

    @Singleton
    @Binds
    abstract fun bindScheduleRepository(
        scheduleRepositoryImpl: ScheduleRepositoryImpl
    ): ScheduleRepository

}