package com.ar.homenetflixtv.di

import com.ar.homenetflixtv.utils.providers.AndroidSchedulerProvider
import com.ar.homenetflixtv.utils.providers.AndroidTestSchedulerProvider
import com.ar.homenetflixtv.utils.providers.SchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class AndroidScheduler

@Qualifier
annotation class AndroidTestScheduler

@Module
@InstallIn(SingletonComponent::class)
abstract class SchedulerModule {

    @AndroidScheduler
    @Singleton
    @Binds
    abstract fun bindSchedulerProvider(
        androidSchedulerProvider: AndroidSchedulerProvider
    ): SchedulerProvider

    @AndroidTestScheduler
    @Singleton
    @Binds
    abstract fun bindTestSchedulerProvider(
        androidTestSchedulerProvider: AndroidTestSchedulerProvider
    ): SchedulerProvider
}