package com.ar.homenetflixtv.playback.statemachine

import com.ar.homenetflixtv.model.Video

/** Represents different states a video can have when it comes to playback. */
sealed class VideoPlaybackState {
    /** Triggers the state to load the video. */
    data class Load(val video: Video) : VideoPlaybackState()

    /** Loading has completed and the player can be prepared. */
    data class Prepare(val video: Video, val startPosition: Long) : VideoPlaybackState()

    /** The video has started playback such as playing after Prepare or resuming after Pause. */
    data class Play(val video: Video) : VideoPlaybackState()

    /** The video is currently paused. */
    data class Pause(val video: Video, val position: Long) : VideoPlaybackState()

    /** The video has ended. */
    data class End(val video: Video) : VideoPlaybackState()

    /** Something terribly wrong occurred. */
    data class Error(val video: Video, val exception: Exception) : VideoPlaybackState()
}

