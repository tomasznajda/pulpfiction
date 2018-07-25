package com.tomasznajda.pulpfiction.event.video

import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.video.VideoListener
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.subjects.PublishSubject


class VideoObservable(private val exoPlayer: SimpleExoPlayer)
    : Observable<VideoEvent>(), VideoListener {

    private val subject = PublishSubject.create<VideoEvent>()

    override fun subscribeActual(observer: Observer<in VideoEvent>) =
            subject
                    .doOnSubscribe { exoPlayer.addVideoListener(this) }
                    .doOnDispose { exoPlayer.removeVideoListener(this) }
                    .subscribe(observer)

    override fun onVideoSizeChanged(width: Int, height: Int, unappliedRotationDegrees: Int, pixelWidthHeightRatio: Float) =
            subject.onNext(VideoSizeChangedEvent(width, height, unappliedRotationDegrees, pixelWidthHeightRatio))

    override fun onRenderedFirstFrame() =
            subject.onNext(RenderedFirstFrameEvent())
}