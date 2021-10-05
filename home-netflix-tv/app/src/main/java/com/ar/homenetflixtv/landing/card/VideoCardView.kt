package com.ar.homenetflixtv.landing.card

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.leanback.widget.BaseCardView
import com.ar.homenetflixtv.R
import com.ar.homenetflixtv.di.GlideRequestManager
import com.ar.homenetflixtv.di.HomeNetflixVideoRepository
import com.ar.homenetflixtv.model.Video
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VideoCardView : BaseCardView {

    /**
     * refs: https://medium.com/androiddevelopers/customizing-leanback-for-android-tv-3f8d4a0b2839
     */

    @GlideRequestManager
    @Inject
    lateinit var glideRequestManager: RequestManager

    private lateinit var title: TextView
    private lateinit var image: ImageView


    constructor(context: Context) : super(context) {
        LayoutInflater.from(getContext()).inflate(R.layout.video_card, this)

        setupUIElements()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.video_card, this)

        setupUIElements()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        LayoutInflater.from(getContext()).inflate(R.layout.video_card, this)

        setupUIElements()
    }

    private fun setupUIElements() {
        image = findViewById(R.id.video_image)
        title = findViewById(R.id.video_title)
    }

    fun bind(video: Video) {
        title.text = video.title
        glideRequestManager
            .load(video.imageUrl)
            .placeholder(ColorDrawable(Color.BLACK))
            .centerCrop()
            .into(image)
    }
}