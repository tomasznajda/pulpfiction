package com.tomasznajda.pulpfiction.event.analitics

import android.net.NetworkInfo
import android.view.Surface
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.analytics.AnalyticsListener
import com.google.android.exoplayer2.decoder.DecoderCounters
import com.google.android.exoplayer2.metadata.Metadata
import com.google.android.exoplayer2.source.MediaSourceEventListener
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.tomasznajda.pulpfiction.__util.ComparableException
import com.tomasznajda.pulpfiction.__util.ComparableIOException
import org.junit.Test

class AnalyticsObservableTest {

    val exoPlayer = mock<SimpleExoPlayer>()
    val observable = AnalyticsObservable(exoPlayer)
    val observer = observable.test()
    val time = mock<AnalyticsListener.EventTime>()

    @Test
    fun `subscribe adds analytics listener`() {
        verify(exoPlayer).addAnalyticsListener(observable)
    }

    @Test
    fun `dispose removes analytics listener `() {
        observer.dispose()
        verify(exoPlayer).removeAnalyticsListener(observable)
    }

    @Test
    fun `onSeekStarted emits SeekProcessedEvent`() {
        observable.onSeekStarted(time)
        observer.assertValue(SeekStartedEvent(time))
    }

    @Test
    fun `onSeekProcessed emits SeekProcessedEvent`() {
        observable.onSeekProcessed(time)
        observer.assertValue(SeekProcessedEvent(time))
    }

    @Test
    fun `onPlaybackParametersChanged emits PlaybackParametersChangedEvent`() {
        val playbackParameters = mock<PlaybackParameters>()
        observable.onPlaybackParametersChanged(time, playbackParameters)
        observer.assertValue(PlaybackParametersChangedEvent(time, playbackParameters))
    }

    @Test
    fun `onPlayerError emits PlayerErrorEvent`() {
        val exception = mock<ExoPlaybackException>()
        observable.onPlayerError(time, exception)
        observer.assertValue(PlayerErrorEvent(time, exception))
    }

    @Test
    fun `onDownstreamFormatChanged emits DownstreamFormatChangedEvent`() {
        val mediaLoadData = mock<MediaSourceEventListener.MediaLoadData>()
        observable.onDownstreamFormatChanged(time, mediaLoadData)
        observer.assertValue(DownstreamFormatChangedEvent(time, mediaLoadData))
    }

    @Test
    fun `onMediaPeriodCreated emits MediaPeriodCreatedEvent`() {
        observable.onMediaPeriodCreated(time)
        observer.assertValue(MediaPeriodCreatedEvent(time))
    }

    @Test
    fun `onMediaPeriodReleased emits MediaPeriodReleasedEvent`() {
        observable.onMediaPeriodReleased(time)
        observer.assertValue(MediaPeriodReleasedEvent(time))
    }

    @Test
    fun `onRenderedFirstFrame emits RenderedFirstFrameEvent`() {
        val surface = mock<Surface>()
        observable.onRenderedFirstFrame(time, surface)
        observer.assertValue(RenderedFirstFrameEvent(time, surface))
    }

    @Test
    fun `onReadingStarted emits ReadingStartedEvent`() {
        observable.onReadingStarted(time)
        observer.assertValue(ReadingStartedEvent(time))
    }

    @Test
    fun `onBandwidthEstimate emits BandwidthEstimateEvent`() {
        observable.onBandwidthEstimate(time, 41, 52, 63)
        observer.assertValue(BandwidthEstimateEvent(time, 41, 52, 63))
    }

    @Test
    fun `onNetworkTypeChanged emits NetworkTypeChangedEvent`() {
        val networkInfo = mock<NetworkInfo>()
        observable.onNetworkTypeChanged(time, networkInfo)
        observer.assertValue(NetworkTypeChangedEvent(time, networkInfo))
    }

    @Test
    fun `onPlayerStateChanged emits PlayerStateChangedEvent`() {
        observable.onPlayerStateChanged(time, true, Player.STATE_BUFFERING)
        observer.assertValue(PlayerStateChangedEvent(time, true, Player.STATE_BUFFERING))
    }

    @Test
    fun `onViewportSizeChange emits ViewportSizeChangeEvent`() {
        observable.onViewportSizeChange(time, 120, 360)
        observer.assertValue(ViewportSizeChangeEvent(time, 120, 360))
    }

    @Test
    fun `onShuffleModeChanged emits ShuffleModeChangedEvent`() {
        observable.onShuffleModeChanged(time, true)
        observer.assertValue(ShuffleModeChangedEvent(time, true))
    }

    @Test
    fun `onTracksChanged emits TracksChangedEvent`() {
        observable.onTracksChanged(time, TrackGroupArray(), TrackSelectionArray())
        observer.assertValue(TracksChangedEvent(time, TrackGroupArray(), TrackSelectionArray()))
    }

    @Test
    fun `onPositionDiscontinuity emits PositionDiscontinuityEvent`() {
        observable.onPositionDiscontinuity(time, 7438)
        observer.assertValue(PositionDiscontinuityEvent(time, 7438))
    }

    @Test
    fun `onRepeatModeChanged emits RepeatModeChangedEvent`() {
        observable.onRepeatModeChanged(time, 7532)
        observer.assertValue(RepeatModeChangedEvent(time, 7532))
    }

    @Test
    fun `onUpstreamDiscarded emits UpstreamDiscardedEvent`() {
        val mediaLoadData = mock<MediaSourceEventListener.MediaLoadData>()
        observable.onUpstreamDiscarded(time, mediaLoadData)
        observer.assertValue(UpstreamDiscardedEvent(time, mediaLoadData))
    }

    @Test
    fun `onTimelineChanged emits TimelineChangedEvent`() {
        observable.onTimelineChanged(time, 1204)
        observer.assertValue(TimelineChangedEvent(time, 1204))
    }

    @Test
    fun `onDroppedVideoFrames emits DroppedVideoFramesEvent`() {
        observable.onDroppedVideoFrames(time, 1204, 129)
        observer.assertValue(DroppedVideoFramesEvent(time, 1204, 129))
    }

    @Test
    fun `onVideoSizeChanged emits VideoSizeChangedEvent`() {
        observable.onVideoSizeChanged(time, 120, 360, 90, .33f)
        observer.assertValue(VideoSizeChangedEvent(time, 120, 360, 90, .33f))
    }

    @Test
    fun `onMetadata emits MetadataEvent`() {
        observable.onMetadata(time, Metadata())
        observer.assertValue(MetadataEvent(time, Metadata()))
    }

    @Test
    fun `onDrmKeysLoaded emits DrmKeysLoadedEvent`() {
        observable.onDrmKeysLoaded(time)
        observer.assertValue(DrmKeysLoadedEvent(time))
    }

    @Test
    fun `onDrmKeysRestored emits DrmKeysRestoredEvent`() {
        observable.onDrmKeysRestored(time)
        observer.assertValue(DrmKeysRestoredEvent(time))
    }

    @Test
    fun `onDrmKeysRemoved emits DrmKeysRemovedEvent`() {
        observable.onDrmKeysRemoved(time)
        observer.assertValue(DrmKeysRemovedEvent(time))
    }

    @Test
    fun `onDrmSessionManagerError emits DrmSessionManagerErrorEvent`() {
        observable.onDrmSessionManagerError(time, ComparableException(371))
        observer.assertValue(DrmSessionManagerErrorEvent(time, ComparableException(371)))
    }

    @Test
    fun `onDecoderInitialized emits DecoderInitializedEvent`() {
        observable.onDecoderInitialized(time, 997, "Police", 1234)
        observer.assertValue(DecoderInitializedEvent(time, 997, "Police", 1234))
    }

    @Test
    fun `onDecoderEnabled emits DecoderEnabledEvent`() {
        val counters = DecoderCounters()
        observable.onDecoderEnabled(time, 997, counters)
        observer.assertValue(DecoderEnabledEvent(time, 997, counters))
    }

    @Test
    fun `onDecoderDisabled emits DecoderDisabledEvent`() {
        val counters = DecoderCounters()
        observable.onDecoderDisabled(time, 997, counters)
        observer.assertValue(DecoderDisabledEvent(time, 997, counters))
    }

    @Test
    fun `onDecoderInputFormatChanged emits DecoderInputFormatChangedEvent`() {
        val format = mock<Format>()
        observable.onDecoderInputFormatChanged(time, 997, format)
        observer.assertValue(DecoderInputFormatChangedEvent(time, 997, format))
    }

    @Test
    fun `onAudioSessionId emits AudioSessionIdEvent`() {
        observable.onAudioSessionId(time, 100)
        observer.assertValue(AudioSessionIdEvent(time, 100))
    }

    @Test
    fun `onAudioUnderrun emits AudioUnderrunEvent`() {
        observable.onAudioUnderrun(time, 999, 123, 12)
        observer.assertValue(AudioUnderrunEvent(time, 999, 123, 12))
    }

    @Test
    fun `onLoadingChanged emits LoadingChangedEvent`() {
        observable.onLoadingChanged(time, true)
        observer.assertValue(LoadingChangedEvent(time, true))
    }

    @Test
    fun `onLoadStarted emits LoadingChangedEvent`() {
        val loadEventInfo = mock<MediaSourceEventListener.LoadEventInfo>()
        val mediaLoadData = mock<MediaSourceEventListener.MediaLoadData>()
        observable.onLoadStarted(time, loadEventInfo, mediaLoadData)
        observer.assertValue(LoadStartedEvent(time, loadEventInfo, mediaLoadData))
    }

    @Test
    fun `onLoadCanceled emits LoadCanceledEvent`() {
        val loadEventInfo = mock<MediaSourceEventListener.LoadEventInfo>()
        val mediaLoadData = mock<MediaSourceEventListener.MediaLoadData>()
        observable.onLoadCanceled(time, loadEventInfo, mediaLoadData)
        observer.assertValue(LoadCanceledEvent(time, loadEventInfo, mediaLoadData))
    }

    @Test
    fun `onLoadCompleted emits LoadCompletedEvent`() {
        val loadEventInfo = mock<MediaSourceEventListener.LoadEventInfo>()
        val mediaLoadData = mock<MediaSourceEventListener.MediaLoadData>()
        observable.onLoadCompleted(time, loadEventInfo, mediaLoadData)
        observer.assertValue(LoadCompletedEvent(time, loadEventInfo, mediaLoadData))
    }

    @Test
    fun `onLoadError emits LoadErrorEvent`() {
        val loadEventInfo = mock<MediaSourceEventListener.LoadEventInfo>()
        val mediaLoadData = mock<MediaSourceEventListener.MediaLoadData>()
        observable.onLoadError(time, loadEventInfo, mediaLoadData, ComparableIOException(21), false)
        observer.assertValue(LoadErrorEvent(time, loadEventInfo, mediaLoadData, ComparableIOException(21), false))
    }
}