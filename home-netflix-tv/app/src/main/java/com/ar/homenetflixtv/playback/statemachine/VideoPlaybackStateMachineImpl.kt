package com.ar.homenetflixtv.playback.statemachine

/**
 * PlaybackStateListener for registering listeners and dispatch events
 */
class VideoPlaybackStateMachineImpl : VideoPlaybackStateMachine {

    private val playbackStateListeners = arrayListOf<VideoPlaybackStateListener>()

    init {
        playbackStateListeners.addAll(createNonUiPlaybackStateListeners())
    }

    /**
     * Adds a [VideoPlaybackStateListener] to be notified of [VideoPlaybackState] changes.
     */
    override fun addPlaybackStateListener(listenerVideo: VideoPlaybackStateListener) {
        playbackStateListeners.add(listenerVideo)
    }

    /**
     * Removes the [VideoPlaybackStateListener] so it receives no further [VideoPlaybackState] changes.
     */
    override fun removePlaybackStateListener(listenerVideo: VideoPlaybackStateListener) {
        playbackStateListeners.remove(listenerVideo)
    }

    override fun onStateChange(state: VideoPlaybackState) {
        playbackStateListeners.forEach {
            it.onChanged(state)
        }
    }

    override fun onDestroy() {
        playbackStateListeners.forEach { it.onDestroy() }
    }

    /**
     * Creates and returns a new List of non-UI [VideoPlaybackStateListener] objects to register with the
     * state machine.
     */
    private fun createNonUiPlaybackStateListeners(): List<VideoPlaybackStateListener> {
        return listOf()
    }

}