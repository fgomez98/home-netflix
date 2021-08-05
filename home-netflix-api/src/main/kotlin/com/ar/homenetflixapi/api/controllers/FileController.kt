package com.ar.homenetflixapi.api.controllers

import com.ar.homenetflixapi.api.dto.VideoDTO
import com.ar.homenetflixapi.service.FileService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/files")
class FileController(val fileService: FileService) {

    @GetMapping
    fun get(): ResponseEntity<List<VideoDTO>> {
        val videosList = fileService.getVideos()
        val videosDtoList = videosList.map { v -> VideoDTO(v.filename, v.relativePath) }
        return ResponseEntity.ok(videosDtoList)
    }
}