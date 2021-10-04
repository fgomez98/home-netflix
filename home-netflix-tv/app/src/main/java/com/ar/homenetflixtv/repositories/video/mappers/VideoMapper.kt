package com.ar.homenetflixtv.repositories.video.mappers

import com.ar.homenetflixtv.model.Video
import com.ar.homenetflixtv.repositories.video.dto.VideoResponseDto

object VideoMapper {

    private fun map(videoResponseDto: VideoResponseDto): Video {
        return Video.Builder(videoResponseDto.filename, videoResponseDto.relativePath)
            .build()
    }

    fun map(videoResponseDtoList: List<VideoResponseDto>): List<Video> {
        return videoResponseDtoList.map { t -> map(t) }
    }
}