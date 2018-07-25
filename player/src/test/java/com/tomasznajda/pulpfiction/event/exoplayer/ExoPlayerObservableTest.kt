package com.tomasznajda.pulpfiction.event.exoplayer

import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class ExoPlayerObservableTest {

    val exoPlayer = mock<SimpleExoPlayer>()
    val observable = ExoPlayerObservable(exoPlayer)
    val observer = observable.test()

    @Test
    fun `subscribe adds event listener`() {
        verify(exoPlayer).addListener(observable)
    }

    @Test
    fun `dispose removes event listener `() {
        observer.dispose()
        verify(exoPlayer).addListener(observable)
    }

    @Test
    fun `onSeekProcessed emits SeekProcessedEvent`() {
        observable.onSeekProcessed()
        observer.assertValue { it is SeekProcessedEvent }
    }

    @Test
    fun `onPlayerError emits PlayerErrorEvent`() {
        val exception = mock<ExoPlaybackException>()
        observable.onPlayerError(exception)
        observer.assertValue(PlayerErrorEvent(exception))
    }

    @Test
    fun `onPlaybackParametersChanged emits PlaybackParametersChangedEvent`() {
        val playbackParameters = mock<PlaybackParameters>()
        observable.onPlaybackParametersChanged(playbackParameters)
        observer.assertValue(PlaybackParametersChangedEvent(playbackParameters))
    }

    @Test
    fun `onTracksChanged emits TracksChangedEvent`() {
        observable.onTracksChanged(TrackGroupArray(), TrackSelectionArray())
        observer.assertValue(TracksChangedEvent(TrackGroupArray(), TrackSelectionArray()))
    }

    @Test
    fun `onLoadingChanged emits LoadingChangedEvent`() {
        observable.onLoadingChanged(true)
        observer.assertValue(LoadingChangedEvent(true))
    }

    @Test
    fun `onPositionDiscontinuity emits PositionDiscontinuityEvent`() {
        observable.onPositionDiscontinuity(7438)
        observer.assertValue(PositionDiscontinuityEvent(7438))
    }

    @Test
    fun `onRepeatModeChanged emits RepeatModeChangedEvent`() {
        observable.onRepeatModeChanged(7532)
        observer.assertValue(RepeatModeChangedEvent(7532))
    }

    @Test
    fun `onShuffleModeEnabledChanged emits ShuffleModeChangedEvent`() {
        observable.onShuffleModeEnabledChanged(false)
        observer.assertValue(ShuffleModeChangedEvent(false))
    }

    @Test
    fun `onTimelineChanged emits TimelineChangedEvent`() {
        observable.onTimelineChanged(Timeline.EMPTY, 583, 1204)
        observer.assertValue(TimelineChangedEvent(Timeline.EMPTY, 583, 1204))
    }

    @Test
    fun `onPlayerStateChanged emits PlayerStateChangedEvent`() {
        observable.onPlayerStateChanged(true, Player.STATE_BUFFERING)
        observer.assertValue(PlayerStateChangedEvent(true, Player.STATE_BUFFERING))
    }
}