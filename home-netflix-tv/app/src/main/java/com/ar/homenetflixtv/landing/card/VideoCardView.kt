package com.ar.homenetflixtv.landing.card

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.ImageCardView
import com.ar.homenetflixtv.R
import com.ar.homenetflixtv.model.Video

class VideoCardView : BaseCardView {

    /**
     * refs: https://medium.com/androiddevelopers/customizing-leanback-for-android-tv-3f8d4a0b2839
     */

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
        title.text = video.getTitle()
        image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fer))

    }
}