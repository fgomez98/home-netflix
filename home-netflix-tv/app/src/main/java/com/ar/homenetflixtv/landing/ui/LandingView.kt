package com.ar.homenetflixtv.landing.ui

import com.ar.homenetflixtv.model.Video

interface LandingView {

    fun showVideos(videos: List<Video>)
}