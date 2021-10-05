package com.ar.homenetflixtv.landing.card.presenter

import android.content.Context
import com.ar.homenetflixtv.landing.card.VideoCardView
import com.ar.homenetflixtv.model.Video

class VideoCardPresenter : AbstractCardPresenter<VideoCardView, Video>() {

    override fun onCreateView(context: Context): VideoCardView {
        val appContext = context.applicationContext
        /*
            todo:
                analizar diferencia entre pasar app context vs activity context que es lo que
                nos llegaria en este caso
         */
        val videoCardView = VideoCardView(context)
        videoCardView.isFocusable = true
        videoCardView.isFocusableInTouchMode = true
        return videoCardView
    }

    override fun onBindViewHolder(model: Video, cardView: VideoCardView) {
        cardView.bind(model)
    }


}