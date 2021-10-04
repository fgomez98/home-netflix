package com.ar.homenetflixtv.repositories.video

import com.ar.homenetflixtv.model.Video
import io.reactivex.Single

interface VideoRepository {

    fun getVideos(): Single<List<Video>>

}
