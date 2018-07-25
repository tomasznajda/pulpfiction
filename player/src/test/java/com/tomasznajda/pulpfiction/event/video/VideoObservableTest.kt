package com.tomasznajda.pulpfiction.event.video

import com.google.android.exoplayer2.SimpleExoPlayer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class VideoObservableTest {

    val exoPlayer = mock<SimpleExoPlayer>()
    val observable = VideoObservable(exoPlayer)
    val observer = observable.test()

    @Test
    fun `subscribe adds video listener`() {
        verify(exoPlayer).addVideoListener(observable)
    }

    @Test
    fun `dispose removes video listener `() {
        observer.dispose()
        verify(exoPlayer).removeVideoListener(observable)
    }

    @Test
    fun `onVideoSizeChanged emits VideoSizeChangedEvent`() {
        observable.onVideoSizeChanged(123, 456, 789, 1.5f)
        observer.assertValue(VideoSizeChangedEvent(123, 456, 789, 1.5f))
    }

    @Test
    fun `onRenderedFirstFrame emits RenderedFirstFrameEvent`() {
        observable.onRenderedFirstFrame()
        observer.assertValue { it is RenderedFirstFrameEvent }
    }
}