package com.ar.homenetflixtv.landing.ui

import com.ar.homenetflixtv.model.Video
import com.ar.homenetflixtv.repositories.video.VideoRepository
import com.ar.homenetflixtv.utils.providers.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

class LandingPresenter(private val videoRepository: VideoRepository,
                       private val schedulerProvider: SchedulerProvider,
                       view: LandingView) {

    private val view: WeakReference<LandingView> = WeakReference(view)
    private val disposables: CompositeDisposable = CompositeDisposable()

    fun onViewDetached() {

    }

    fun onViewAttached() {
        loadVideos()
    }

    private fun loadVideos() {
        disposables.add(videoRepository.getVideos()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ videos: List<Video> -> receivedVideos(videos) }) { throwable -> onLoadVideosError(throwable) })
    }

    private fun receivedVideos(videos: List<Video>) {
        view.get()?.showVideos(videos)
    }

    private fun onLoadVideosError(throwable: Throwable) {
        view.get()?.showVideosError()
    }


}