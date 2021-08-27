package com.ar.homenetflixtv.landing.ui

import com.ar.homenetflixtv.model.Video
import java.lang.ref.WeakReference

class LandingPresenter(view: LandingView) {

    private val view: WeakReference<LandingView> = WeakReference(view)

    fun onViewDetached() {

    }

    fun onViewAttached() {
        view.get()?.showVideos(Video.videos)
    }


}