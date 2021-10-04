package com.ar.homenetflixtv.repositories.video.api

import com.ar.homenetflixtv.repositories.video.dto.VideoResponseDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET


interface VideoService {

    @GET("files")
    fun getAllVideos(): Single<Response<List<VideoResponseDto>>>

}