package com.ar.homenetflixtv.landing.card

import android.content.Context
import com.ar.homenetflixtv.model.Video

class VideoCardPresenter : AbstractCardPresenter<VideoCardView, Video>() {

    override fun onCreateView(context: Context): VideoCardView {
        val appContext = context.applicationContext
        val videoCardView = VideoCardView(appContext)
        videoCardView.isFocusable = true
        videoCardView.isFocusableInTouchMode = true
        return videoCardView
    }

    override fun onBindViewHolder(model: Video, cardView: VideoCardView) {
        cardView.bind(model)
    }


}