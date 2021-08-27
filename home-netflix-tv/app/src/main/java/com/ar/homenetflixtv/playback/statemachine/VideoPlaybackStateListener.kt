package com.ar.homenetflixtv.playback.statemachine

/**
 * Listens to changes in [VideoPlaybackState].
 */
interface VideoPlaybackStateListener {

    /**
     * Called with a [VideoPlaybackState] whenever the playback state machine changes states.
     */
    fun onChanged(state: VideoPlaybackState)

    /**
     * Called when the listener is being destroyed and should clean up any references.
     */
    fun onDestroy() {}


}