package com.ar.homenetflixtv.repositories.video

import com.ar.homenetflixtv.di.HomeNetflixVideoServiceApi
import com.ar.homenetflixtv.model.Video
import com.ar.homenetflixtv.repositories.video.api.VideoService
import com.ar.homenetflixtv.repositories.video.mappers.VideoMapper
import com.ar.homenetflixtv.utils.networking.RetrofitUtils
import io.reactivex.Single
import javax.inject.Inject

class RestVideoRepository @Inject constructor(
    @HomeNetflixVideoServiceApi private val videoService: VideoService
) : VideoRepository {

    override fun getVideos(): Single<List<Video>> {
        return RetrofitUtils.performRequest(videoService.getAllVideos()).map(VideoMapper::map)
    }
}