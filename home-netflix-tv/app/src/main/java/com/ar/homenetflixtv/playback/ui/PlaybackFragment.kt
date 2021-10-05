package com.ar.homenetflixtv.playback.ui

//import com.google.android.exoplayer2.ext.rtmp.RtmpDataSourceFactory
import android.os.Build
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import android.view.View
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import com.ar.homenetflixtv.model.Video
import com.ar.homenetflixtv.playback.expoplayer.ProgressTransportControlGlue
import com.ar.homenetflixtv.playback.expoplayer.SingleVideoQueueNavigator
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.ext.leanback.LeanbackPlayerAdapter
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.google.android.exoplayer2.ext.rtmp.RtmpDataSourceFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import dagger.hilt.android.AndroidEntryPoint
import java.time.Duration

@AndroidEntryPoint
class PlaybackFragment : VideoSupportFragment(), PlaybackView {
//https://www.youtube.com/watch?v=svdq1BWl4r8

    private var exoplayer: ExoPlayer? = null
    private lateinit var mediaSession: MediaSessionCompat
    private lateinit var mediaSessionConnector: MediaSessionConnector

    private lateinit var presenter: PlaybackPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create presenter that will be used throughout the lifecycle of this Fragment.
        createPresenter()
    }

    override fun setKeepScreenOn(value: Boolean) {
        view?.keepScreenOn = value
    }

    private fun createPresenter() {
        val video = requireActivity().intent.getSerializableExtra("video") as Video
        presenter = PlaybackPresenter(video, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewAttached()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onViewDetached()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        destroyPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()

        // Releasing the mediaSession due to inactive playback and setting token for cast to null.
        mediaSession.release()
    }

    override fun initializePlayer(video: Video) {
        // Create the MediaSession that will be used throughout the lifecycle of this Fragment.
        createMediaSession(video)

        /*
        val dataSourceFactory = DefaultDataSourceFactory(
            requireContext(),
            Util.getUserAgent(requireContext(), getString(R.string.app_name))
        )

        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri((video.getUri())))
        */


        /*
            TODO: Investigar de que se trata el DefaultBandwidthMeter
        val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory()
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        */

        val trackSelector = DefaultTrackSelector(requireContext()).apply {
            setParameters(buildUponParameters().setMaxVideoSizeSd())
        }

        // Create RTMP Data Source
        val rtmpDataSourceFactory = RtmpDataSourceFactory()

        val mediaSource = ProgressiveMediaSource
            .Factory(rtmpDataSourceFactory)
            .createMediaSource(MediaItem.fromUri(video.uri))

        exoplayer = SimpleExoPlayer.Builder(requireContext())
            .setTrackSelector(trackSelector)
            .build().apply {
                setMediaSource(mediaSource)
                prepare()
                addListener(PlayerEventListener())
                prepareGlue(video, this)
                mediaSessionConnector.setPlayer(this)
                mediaSession.isActive = true
            }

    }

    private fun destroyPlayer() {
        mediaSession.isActive = false
        mediaSessionConnector.setPlayer(null)
        exoplayer?.let {
            // Pause the player to notify listeners before it is released.
            it.pause()
            it.release()
            exoplayer = null
        }
    }

    /*
        The code that glues your app together should be inside the PlaybackSupportFragment or
        VideoSupportFragment that defines the UI.
     */
    private fun prepareGlue(video: Video, localExoplayer: ExoPlayer) {
        ProgressTransportControlGlue(
            requireContext(),
            LeanbackPlayerAdapter(
                requireContext(),
                localExoplayer,
                PLAYER_UPDATE_INTERVAL_MILLIS.toInt()
            ),
            onProgressUpdate
        ).apply {
            host = VideoSupportFragmentGlueHost(this@PlaybackFragment)
            title = video.title
            // Enable seek manually since PlaybackTransportControlGlue.getSeekProvider() is null,
            // so that PlayerAdapter.seekTo(long) will be called during user seeking.
            // TODO(gargsahil@): Add a PlaybackSeekDataProvider to support video scrubbing.
            isSeekEnabled = true
        }
    }

    private fun createMediaSession(video: Video) {
        mediaSession = MediaSessionCompat(requireContext(), MEDIA_SESSION_TAG)

        mediaSessionConnector = MediaSessionConnector(mediaSession).apply {
            setQueueNavigator(SingleVideoQueueNavigator(video, mediaSession))
            setControlDispatcher(object : DefaultControlDispatcher() {
                override fun dispatchStop(player: Player, reset: Boolean): Boolean {
                    // Treat stop commands as pause, this keeps ExoPlayer, MediaSession, etc.
                    // in memory to allow for quickly resuming. This also maintains the playback
                    // position so that the user will resume from the current position when backing
                    // out and returning to this video
                    // This both prevents playback from starting automatically and pauses it if
                    // it's already playing
                    player.playWhenReady = false
                    return true
                }
            })
        }
    }

    private fun startPlaybackFromWatchProgress(startPosition: Long) {
        exoplayer?.apply {
            seekTo(startPosition)
            playWhenReady = true
        }
    }

    private val onProgressUpdate: () -> Unit = {
        // TODO(benbaxter): Calculate when end credits are displaying and show the next episode for
        //  episodic content.
    }

    inner class PlayerEventListener : Player.Listener {

        override fun onPlayerError(error: ExoPlaybackException) {
            presenter.onPlayerError(error)
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            presenter.onIsPlayingChanged(isPlaying, exoplayer!!.currentPosition)
        }
    }

    override fun finishedPlayingMedia(): Boolean {
        return exoplayer!!.playbackState == Player.STATE_ENDED
    }

    companion object {
        // Update the player UI fairly often. The frequency of updates affects several UI components
        // such as the smoothness of the progress bar and time stamp labels updating. This value can
        // be tweaked for better performance.
        private val PLAYER_UPDATE_INTERVAL_MILLIS =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Duration.ofMillis(50).toMillis()
            } else {
                TODO("VERSION.SDK_INT < O")
            }

        // A short name to identify the media session when debugging.
        private const val MEDIA_SESSION_TAG = "ReferenceAppKotlin"
    }
}