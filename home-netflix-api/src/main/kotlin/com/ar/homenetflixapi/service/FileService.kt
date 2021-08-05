package com.ar.homenetflixapi.service

import com.ar.homenetflixapi.model.Video

interface FileService {

    fun getVideos(): List<Video>

}