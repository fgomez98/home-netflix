package com.ar.homenetflixtv.landing

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.ar.homenetflixtv.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_activity)
    }
}