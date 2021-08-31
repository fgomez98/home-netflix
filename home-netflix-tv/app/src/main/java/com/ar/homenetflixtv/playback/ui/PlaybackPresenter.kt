package com.ar.homenetflixtv.playback.ui

import com.ar.homenetflixtv.model.Video
import com.ar.homenetflixtv.playback.statemachine.VideoPlaybackState
import com.ar.homenetflixtv.playback.statemachine.VideoPlaybackStateListener
import com.ar.homenetflixtv.playback.statemachine.VideoPlaybackStateMachine
import com.ar.homenetflixtv.playback.statemachine.VideoPlaybackStateMachineImpl
import com.google.android.exoplayer2.ExoPlaybackException
import java.lang.ref.WeakReference

class PlaybackPresenter(view: PlaybackView) : VideoPlaybackStateListener {

    // Get the video data.
    // fixme: hardcoded just for testing
    private val video = Video(
        "Test",
        "rtmp://192.168.5.10:1935/vod2/bbb.mp4",
//        "https://storage.googleapis.com/atv-reference-app-videos/clips-supercharged/supercharged-jquery-and-closest.mp4"
    "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Big_buck_bunny_poster_big.jpg/1024px-Big_buck_bunny_poster_big.jpg"
    )
    private val stateMachineVideo: VideoPlaybackStateMachine = VideoPlaybackStateMachineImpl()
    private val view: WeakReference<PlaybackView> = WeakReference(view)

    fun onViewDetached() {
        stateMachineVideo.removePlaybackStateListener(this)
    }

    fun onViewAttached() {
        stateMachineVideo.addPlaybackStateListener(this)
    }

    override fun onChanged(state: VideoPlaybackState) {
        // While a video is playing, the screen should stay on and the device should not go to
        // sleep. When in any other state such as if the user pauses the video, the app should
        // not prevent the device from going to sleep.
        view.get()?.setKeepScreenOn(state is VideoPlaybackState.Play)

        when (state) {
            is VideoPlaybackState.Prepare -> {
            } // fixme: startPlaybackFromWatchProgress(state.startPosition)
            is VideoPlaybackState.End -> {
                // To get to playback, the user always goes through browse first. Deep links for
                // directly playing a video also go to browse before playback. If playback
                // finishes the entire video, the PlaybackFragment is popped off the back stack
                // and the user returns to browse.

                // Do nothing.
            }
            is VideoPlaybackState.Error -> {
                // Do nothing.
            }
            is VideoPlaybackState.Play -> {
                if (view.get()!!.finishedPlayingMedia()) {
                    stateMachineVideo.onStateChange(VideoPlaybackState.End(video))
                }
            }
            else -> {
                // Do nothing.
            }
        }
    }

    fun onStart() {
        view.get()?.initializePlayer(video)
        stateMachineVideo.onStateChange(VideoPlaybackState.Load(video))
    }

    fun onPlayerError(error: ExoPlaybackException) {
        stateMachineVideo.onStateChange(VideoPlaybackState.Error(video, error))
    }

    fun onIsPlayingChanged(isPlaying: Boolean, playerPosition: Long) {
        when {
            isPlaying -> stateMachineVideo.onStateChange(
                VideoPlaybackState.Play(video)
            )
            else -> stateMachineVideo.onStateChange(
                VideoPlaybackState.Pause(video, playerPosition)
            )
        }
    }
}