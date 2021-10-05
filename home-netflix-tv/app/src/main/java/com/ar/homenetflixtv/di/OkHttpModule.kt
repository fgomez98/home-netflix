package com.ar.homenetflixtv.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GlideOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitOkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object OkHttpModule {

    @GlideOkHttpClient
    @Singleton
    @Provides
    fun provideGlideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @RetrofitOkHttpClient
    @Singleton
    @Provides
    fun provideRetrofitOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }
}