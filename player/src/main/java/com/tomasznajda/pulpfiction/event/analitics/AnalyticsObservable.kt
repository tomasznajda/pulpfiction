package com.tomasznajda.pulpfiction.event.analitics

import android.net.NetworkInfo
import android.view.Surface
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Format
import com.google.android.exoplayer2.PlaybackParameters
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.analytics.AnalyticsListener
import com.google.android.exoplayer2.decoder.DecoderCounters
import com.google.android.exoplayer2.metadata.Metadata
import com.google.android.exoplayer2.source.MediaSourceEventListener
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.subjects.PublishSubject
import java.io.IOException
import java.lang.Exception


class AnalyticsObservable(private val exoPlayer: SimpleExoPlayer)
    : Observable<AnalyticsEvent>(), AnalyticsListener {

    private val subject = PublishSubject.create<AnalyticsEvent>()

    override fun subscribeActual(observer: Observer<in AnalyticsEvent>) =
            subject
                    .doOnSubscribe { exoPlayer.addAnalyticsListener(this) }
                    .doOnDispose { exoPlayer.removeAnalyticsListener(this) }
                    .subscribe(observer)

    override fun onSeekStarted(eventTime: AnalyticsListener.EventTime) =
            subject.onNext(SeekStartedEvent(eventTime))

    override fun onSeekProcessed(eventTime: AnalyticsListener.EventTime) =
            subject.onNext(SeekProcessedEvent(eventTime))

    override fun onPlaybackParametersChanged(eventTime: AnalyticsListener.EventTime, playbackParameters: PlaybackParameters) =
            subject.onNext(PlaybackParametersChangedEvent(eventTime, playbackParameters))

    override fun onPlayerError(eventTime: AnalyticsListener.EventTime, error: ExoPlaybackException) =
            subject.onNext(PlayerErrorEvent(eventTime, error))

    override fun onDownstreamFormatChanged(eventTime: AnalyticsListener.EventTime, mediaLoadData: MediaSourceEventListener.MediaLoadData) =
            subject.onNext(DownstreamFormatChangedEvent(eventTime, mediaLoadData))

    override fun onMediaPeriodCreated(eventTime: AnalyticsListener.EventTime) =
            subject.onNext(MediaPeriodCreatedEvent(eventTime))

    override fun onMediaPeriodReleased(eventTime: AnalyticsListener.EventTime) =
            subject.onNext(MediaPeriodReleasedEvent(eventTime))

    override fun onRenderedFirstFrame(eventTime: AnalyticsListener.EventTime, surface: Surface) =
            subject.onNext(RenderedFirstFrameEvent(eventTime, surface))

    override fun onReadingStarted(eventTime: AnalyticsListener.EventTime) =
            subject.onNext(ReadingStartedEvent(eventTime))

    override fun onBandwidthEstimate(eventTime: AnalyticsListener.EventTime, totalLoadTimeMs: Int, totalBytesLoaded: Long, bitrateEstimate: Long) =
            subject.onNext(BandwidthEstimateEvent(eventTime, totalLoadTimeMs, totalBytesLoaded, bitrateEstimate))

    override fun onNetworkTypeChanged(eventTime: AnalyticsListener.EventTime, networkInfo: NetworkInfo?) =
            subject.onNext(NetworkTypeChangedEvent(eventTime, networkInfo))

    override fun onPlayerStateChanged(eventTime: AnalyticsListener.EventTime, playWhenReady: Boolean, playbackState: Int) =
            subject.onNext(PlayerStateChangedEvent(eventTime, playWhenReady, playbackState))

    override fun onViewportSizeChange(eventTime: AnalyticsListener.EventTime, width: Int, height: Int) =
            subject.onNext(ViewportSizeChangeEvent(eventTime, width, height))

    override fun onShuffleModeChanged(eventTime: AnalyticsListener.EventTime, shuffleModeEnabled: Boolean) =
            subject.onNext(ShuffleModeChangedEvent(eventTime, shuffleModeEnabled))

    override fun onTracksChanged(eventTime: AnalyticsListener.EventTime, trackGroups: TrackGroupArray, trackSelections: TrackSelectionArray) =
            subject.onNext(TracksChangedEvent(eventTime, trackGroups, trackSelections))

    override fun onPositionDiscontinuity(eventTime: AnalyticsListener.EventTime, reason: Int) =
            subject.onNext(PositionDiscontinuityEvent(eventTime, reason))

    override fun onRepeatModeChanged(eventTime: AnalyticsListener.EventTime, repeatMode: Int) =
            subject.onNext(RepeatModeChangedEvent(eventTime, repeatMode))

    override fun onUpstreamDiscarded(eventTime: AnalyticsListener.EventTime, mediaLoadData: MediaSourceEventListener.MediaLoadData) =
            subject.onNext(UpstreamDiscardedEvent(eventTime, mediaLoadData))

    override fun onTimelineChanged(eventTime: AnalyticsListener.EventTime, reason: Int) =
            subject.onNext(TimelineChangedEvent(eventTime, reason))

    override fun onDroppedVideoFrames(eventTime: AnalyticsListener.EventTime, droppedFrames: Int, elapsedMs: Long) =
            subject.onNext(DroppedVideoFramesEvent(eventTime, droppedFrames, elapsedMs))

    override fun onVideoSizeChanged(eventTime: AnalyticsListener.EventTime, width: Int, height: Int, unappliedRotationDegrees: Int, pixelWidthHeightRatio: Float) =
            subject.onNext(VideoSizeChangedEvent(eventTime, width, height, unappliedRotationDegrees, pixelWidthHeightRatio))

    override fun onMetadata(eventTime: AnalyticsListener.EventTime, metadata: Metadata) =
            subject.onNext(MetadataEvent(eventTime, metadata))

    override fun onDrmKeysLoaded(eventTime: AnalyticsListener.EventTime) =
            subject.onNext(DrmKeysLoadedEvent(eventTime))

    override fun onDrmKeysRestored(eventTime: AnalyticsListener.EventTime) =
            subject.onNext(DrmKeysRestoredEvent(eventTime))

    override fun onDrmKeysRemoved(eventTime: AnalyticsListener.EventTime) =
            subject.onNext(DrmKeysRemovedEvent(eventTime))

    override fun onDrmSessionManagerError(eventTime: AnalyticsListener.EventTime, error: Exception) =
            subject.onNext(DrmSessionManagerErrorEvent(eventTime, error))

    override fun onDecoderInitialized(eventTime: AnalyticsListener.EventTime, trackType: Int, decoderName: String, initializationDurationMs: Long) =
            subject.onNext(DecoderInitializedEvent(eventTime, trackType, decoderName, initializationDurationMs))

    override fun onDecoderEnabled(eventTime: AnalyticsListener.EventTime, trackType: Int, decoderCounters: DecoderCounters) =
            subject.onNext(DecoderEnabledEvent(eventTime, trackType, decoderCounters))

    override fun onDecoderDisabled(eventTime: AnalyticsListener.EventTime, trackType: Int, decoderCounters: DecoderCounters) =
            subject.onNext(DecoderDisabledEvent(eventTime, trackType, decoderCounters))

    override fun onDecoderInputFormatChanged(eventTime: AnalyticsListener.EventTime, trackType: Int, format: Format) =
            subject.onNext(DecoderInputFormatChangedEvent(eventTime, trackType, format))

    override fun onAudioSessionId(eventTime: AnalyticsListener.EventTime, audioSessionId: Int) =
            subject.onNext(AudioSessionIdEvent(eventTime, audioSessionId))

    override fun onAudioUnderrun(eventTime: AnalyticsListener.EventTime, bufferSize: Int, bufferSizeMs: Long, elapsedSinceLastFeedMs: Long) =
            subject.onNext(AudioUnderrunEvent(eventTime, bufferSize, bufferSizeMs, elapsedSinceLastFeedMs))

    override fun onLoadingChanged(eventTime: AnalyticsListener.EventTime, isLoading: Boolean) =
            subject.onNext(LoadingChangedEvent(eventTime, isLoading))

    override fun onLoadStarted(eventTime: AnalyticsListener.EventTime, loadEventInfo: MediaSourceEventListener.LoadEventInfo, mediaLoadData: MediaSourceEventListener.MediaLoadData) =
            subject.onNext(LoadStartedEvent(eventTime, loadEventInfo, mediaLoadData))

    override fun onLoadCanceled(eventTime: AnalyticsListener.EventTime, loadEventInfo: MediaSourceEventListener.LoadEventInfo, mediaLoadData: MediaSourceEventListener.MediaLoadData) =
            subject.onNext(LoadCanceledEvent(eventTime, loadEventInfo, mediaLoadData))

    override fun onLoadCompleted(eventTime: AnalyticsListener.EventTime, loadEventInfo: MediaSourceEventListener.LoadEventInfo, mediaLoadData: MediaSourceEventListener.MediaLoadData) =
            subject.onNext(LoadCompletedEvent(eventTime, loadEventInfo, mediaLoadData))

    override fun onLoadError(eventTime: AnalyticsListener.EventTime, loadEventInfo: MediaSourceEventListener.LoadEventInfo, mediaLoadData: MediaSourceEventListener.MediaLoadData, error: IOException, wasCanceled: Boolean) =
            subject.onNext(LoadErrorEvent(eventTime, loadEventInfo, mediaLoadData, error, wasCanceled))
}