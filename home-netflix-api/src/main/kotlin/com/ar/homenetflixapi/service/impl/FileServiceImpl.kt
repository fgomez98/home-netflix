package com.ar.homenetflixapi.service.impl

import com.ar.homenetflixapi.model.Video
import com.ar.homenetflixapi.service.FileService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File

@Service
class FileServiceImpl : FileService {

    @Value("\${video.abspath}")
    private lateinit var pathname: String

    @Value("\${streaming-service-baseurl}")
    private lateinit var streamingServiceBaseUrl: String

    override fun getVideos(): List<Video> {
        val videos = mutableListOf<Video>()
        File(pathname).walk().forEach {
            when {
                it.isFile && it.extension == "mp4" -> videos.add(Video(it.nameWithoutExtension, streamingServiceBaseUrl.plus(it.relativeTo(it.parentFile).toString())))
            }
        }
        return videos
    }
}