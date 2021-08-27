package com.ar.homenetflixtv.playback.statemachine

/**
 * Notifies listeners when the state of playback changes. The new state is supplied but if a
 * listener needs to know the previous state, they must maintain that previous state themselves.
 *
 * Note: *Do not mock!* Use FakePlaybackStateMachine instead when writing tests.
 */
interface VideoPlaybackStateMachine {

    fun onStateChange(state: VideoPlaybackState)

    fun addPlaybackStateListener(listenerVideo: VideoPlaybackStateListener)

    fun removePlaybackStateListener(listenerVideo: VideoPlaybackStateListener)

    fun onDestroy()

}
