package com.ar.homenetflixtv.repositories.video

import com.ar.homenetflixtv.model.Video
import com.ar.homenetflixtv.repositories.video.api.VideoService
import com.ar.homenetflixtv.repositories.video.mappers.VideoMapper
import com.ar.homenetflixtv.utils.networking.RetrofitUtils
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestVideoRepository : VideoRepository {

    // for local development only
    private val BASE_URL = "http://10.0.2.2:9090/home-netflix-api/"
    private val client = OkHttpClient.Builder().build()
    private val videoService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(VideoService::class.java)

    override fun getVideos(): Single<List<Video>> {
        return RetrofitUtils.performRequest(videoService.getAllVideos()).map(VideoMapper::map)
    }
}