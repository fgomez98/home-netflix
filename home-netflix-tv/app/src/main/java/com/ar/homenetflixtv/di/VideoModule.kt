package com.ar.homenetflixtv.di

import com.ar.homenetflixtv.repositories.video.RestVideoRepository
import com.ar.homenetflixtv.repositories.video.VideoRepository
import com.ar.homenetflixtv.repositories.video.api.VideoService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HomeNetflixVideoServiceApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HomeNetflixVideoRepository

@Module
@InstallIn(SingletonComponent::class)
object VideoServiceModule {

    private val BASE_URL = "http://10.0.2.2:9090/home-netflix-api/"

    @HomeNetflixVideoServiceApi
    @Singleton
    @Provides
    fun provideVideoServiceApi(
        @RetrofitOkHttpClient okHttpClient: OkHttpClient
    ): VideoService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(VideoService::class.java)
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class VideoRepositoryModule {

    @HomeNetflixVideoRepository
    @Singleton
    @Binds
    abstract fun bindVideoRepository(
        restVideoRepository: RestVideoRepository
    ): VideoRepository

}