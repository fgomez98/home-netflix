package com.ar.homenetflixtv.playback.ui

import com.ar.homenetflixtv.model.Video

interface PlaybackView {

    /**
     * Sets keepScreenOn setting on View, if True screen will not go to sleep.
     *
     * @param value
     */
    fun setKeepScreenOn(value: Boolean)

    fun initializePlayer(video: Video)

    fun finishedPlayingMedia(): Boolean

}