package com.ar.homenetflixtv.landing.card

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.leanback.widget.BaseCardView
import com.ar.homenetflixtv.R
import com.ar.homenetflixtv.model.Video

class VideoCardView : BaseCardView {

    private lateinit var previewImage: ImageView
    private lateinit var title: TextView

    constructor(context: Context) : super(context) {
        inflate(context, R.layout.video_card, this)
        setupUIElements()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        inflate(context, R.layout.video_card, this)
        setupUIElements()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        inflate(context, R.layout.video_card, this)
        setupUIElements()
    }

    private fun setupUIElements() {
        title = findViewById(R.id.video_title)
        previewImage = findViewById(R.id.video_preview_image)
    }

    fun bind(video: Video) {
        title.text = video.getTitle()
    }
}