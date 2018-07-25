package com.tomasznajda.pulpfiction.event.analitics

import android.net.NetworkInfo
import android.view.Surface
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Format
import com.google.android.exoplayer2.PlaybackParameters
import com.google.android.exoplayer2.analytics.AnalyticsListener
import com.google.android.exoplayer2.decoder.DecoderCounters
import com.google.android.exoplayer2.metadata.Metadata
import com.google.android.exoplayer2.source.MediaSourceEventListener
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import java.io.IOException
import java.lang.Exception

sealed class AnalyticsEvent(open val eventTime: AnalyticsListener.EventTime)

data class SeekStartedEvent(override val eventTime: AnalyticsListener.EventTime) : AnalyticsEvent(eventTime)
data class SeekProcessedEvent(override val eventTime: AnalyticsListener.EventTime) : AnalyticsEvent(eventTime)
data class PlaybackParametersChangedEvent(override val eventTime: AnalyticsListener.EventTime, val playbackParameters: PlaybackParameters) : AnalyticsEvent(eventTime)
data class PlayerErrorEvent(override val eventTime: AnalyticsListener.EventTime, val error: ExoPlaybackException) : AnalyticsEvent(eventTime)
data class DownstreamFormatChangedEvent(override val eventTime: AnalyticsListener.EventTime, val mediaLoadData: MediaSourceEventListener.MediaLoadData) : AnalyticsEvent(eventTime)
data class MediaPeriodCreatedEvent(override val eventTime: AnalyticsListener.EventTime) : AnalyticsEvent(eventTime)
data class MediaPeriodReleasedEvent(override val eventTime: AnalyticsListener.EventTime) : AnalyticsEvent(eventTime)
data class RenderedFirstFrameEvent(override val eventTime: AnalyticsListener.EventTime, val surface: Surface) : AnalyticsEvent(eventTime)
data class ReadingStartedEvent(override val eventTime: AnalyticsListener.EventTime) : AnalyticsEvent(eventTime)
data class BandwidthEstimateEvent(override val eventTime: AnalyticsListener.EventTime, val totalLoadTimeMs: Int, val totalBytesLoaded: Long, val bitrateEstimate: Long) : AnalyticsEvent(eventTime)
data class NetworkTypeChangedEvent(override val eventTime: AnalyticsListener.EventTime, val networkInfo: NetworkInfo?) : AnalyticsEvent(eventTime)
data class PlayerStateChangedEvent(override val eventTime: AnalyticsListener.EventTime, val playWhenReady: Boolean, val playbackState: Int) : AnalyticsEvent(eventTime)
data class ViewportSizeChangeEvent(override val eventTime: AnalyticsListener.EventTime, val width: Int, val height: Int) : AnalyticsEvent(eventTime)
data class ShuffleModeChangedEvent(override val eventTime: AnalyticsListener.EventTime, val shuffleModeEnabled: Boolean) : AnalyticsEvent(eventTime)
data class TracksChangedEvent(override val eventTime: AnalyticsListener.EventTime, val trackGroups: TrackGroupArray, val trackSelections: TrackSelectionArray) : AnalyticsEvent(eventTime)
data class PositionDiscontinuityEvent(override val eventTime: AnalyticsListener.EventTime, val reason: Int) : AnalyticsEvent(eventTime)
data class RepeatModeChangedEvent(override val eventTime: AnalyticsListener.EventTime, val repeatMode: Int) : AnalyticsEvent(eventTime)
data class UpstreamDiscardedEvent(override val eventTime: AnalyticsListener.EventTime, val mediaLoadData: MediaSourceEventListener.MediaLoadData) : AnalyticsEvent(eventTime)
data class TimelineChangedEvent(override val eventTime: AnalyticsListener.EventTime, val reason: Int) : AnalyticsEvent(eventTime)
data class DroppedVideoFramesEvent(override val eventTime: AnalyticsListener.EventTime, val droppedFrames: Int, val elapsedMs: Long) : AnalyticsEvent(eventTime)
data class VideoSizeChangedEvent(override val eventTime: AnalyticsListener.EventTime, val width: Int, val height: Int, val unappliedRotationDegrees: Int, val pixelWidthHeightRatio: Float) : AnalyticsEvent(eventTime)
data class MetadataEvent(override val eventTime: AnalyticsListener.EventTime, val metadata: Metadata) : AnalyticsEvent(eventTime)
data class DrmKeysLoadedEvent(override val eventTime: AnalyticsListener.EventTime) : AnalyticsEvent(eventTime)
data class DrmKeysRestoredEvent(override val eventTime: AnalyticsListener.EventTime) : AnalyticsEvent(eventTime)
data class DrmKeysRemovedEvent(override val eventTime: AnalyticsListener.EventTime) : AnalyticsEvent(eventTime)
data class DrmSessionManagerErrorEvent(override val eventTime: AnalyticsListener.EventTime, val error: Exception) : AnalyticsEvent(eventTime)
data class DecoderInitializedEvent(override val eventTime: AnalyticsListener.EventTime, val trackType: Int, val decoderName: String, val initializationDurationMs: Long) : AnalyticsEvent(eventTime)
data class DecoderEnabledEvent(override val eventTime: AnalyticsListener.EventTime, val trackType: Int, val decoderCounters: DecoderCounters) : AnalyticsEvent(eventTime)
data class DecoderDisabledEvent(override val eventTime: AnalyticsListener.EventTime, val trackType: Int, val decoderCounters: DecoderCounters) : AnalyticsEvent(eventTime)
data class DecoderInputFormatChangedEvent(override val eventTime: AnalyticsListener.EventTime, val trackType: Int, val format: Format) : AnalyticsEvent(eventTime)
data class AudioSessionIdEvent(override val eventTime: AnalyticsListener.EventTime, val audioSessionId: Int) : AnalyticsEvent(eventTime)
data class AudioUnderrunEvent(override val eventTime: AnalyticsListener.EventTime, val bufferSize: Int, val bufferSizeMs: Long, val elapsedSinceLastFeedMs: Long) : AnalyticsEvent(eventTime)
data class LoadingChangedEvent(override val eventTime: AnalyticsListener.EventTime, val isLoading: Boolean) : AnalyticsEvent(eventTime)
data class LoadStartedEvent(override val eventTime: AnalyticsListener.EventTime, val loadEventInfo: MediaSourceEventListener.LoadEventInfo, val mediaLoadData: MediaSourceEventListener.MediaLoadData) : AnalyticsEvent(eventTime)
data class LoadCanceledEvent(override val eventTime: AnalyticsListener.EventTime, val loadEventInfo: MediaSourceEventListener.LoadEventInfo, val mediaLoadData: MediaSourceEventListener.MediaLoadData) : AnalyticsEvent(eventTime)
data class LoadCompletedEvent(override val eventTime: AnalyticsListener.EventTime, val loadEventInfo: MediaSourceEventListener.LoadEventInfo, val mediaLoadData: MediaSourceEventListener.MediaLoadData) : AnalyticsEvent(eventTime)
data class LoadErrorEvent(override val eventTime: AnalyticsListener.EventTime, val loadEventInfo: MediaSourceEventListener.LoadEventInfo, val mediaLoadData: MediaSourceEventListener.MediaLoadData, val error: IOException, val wasCanceled: Boolean) : AnalyticsEvent(eventTime)
