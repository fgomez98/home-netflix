package com.ar.homenetflixtv.playback.expoplayer

import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.annotation.VisibleForTesting
import com.ar.homenetflixtv.model.Video
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ext.mediasession.TimelineQueueNavigator

/**
 * A QueueNavigator that handles a single video.
 *
 * This QueueNavigator provides the MediaDescriptionCompat for the passed Video. Since it handles
 * a single video only, it can build the description once it is first requested and then reuse
 * that description for all future requests.
 */
class SingleVideoQueueNavigator(video: Video, mediaSession: MediaSessionCompat) :
    TimelineQueueNavigator(mediaSession) {
    private val mediaDescriptionCompat by lazy { getMediaDescription(video) }

    override fun getMediaDescription(player: Player, windowIndex: Int): MediaDescriptionCompat {
        return mediaDescriptionCompat
    }

    @VisibleForTesting
    fun getMediaDescription(video: Video): MediaDescriptionCompat {
        return MediaDescriptionCompat.Builder()
            .setTitle(video.title)
            .build()
    }
}