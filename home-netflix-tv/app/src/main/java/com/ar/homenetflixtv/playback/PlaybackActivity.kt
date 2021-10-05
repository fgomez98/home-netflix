package com.ar.homenetflixtv.playback

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.ar.homenetflixtv.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaybackActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_activity)
    }

}