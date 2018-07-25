package com.tomasznajda.pulpfiction.event.exoplayer

import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.PlaybackParameters
import com.google.android.exoplayer2.Timeline
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray

sealed class PlayerEvent

class SeekProcessedEvent : PlayerEvent()
data class PlayerErrorEvent(val error: ExoPlaybackException) : PlayerEvent()
data class PlaybackParametersChangedEvent(val playbackParameters: PlaybackParameters) : PlayerEvent()
data class TracksChangedEvent(val trackGroups: TrackGroupArray, val trackSelections: TrackSelectionArray) : PlayerEvent()
data class LoadingChangedEvent(val isLoading: Boolean) : PlayerEvent()
data class PositionDiscontinuityEvent(val reason: Int) : PlayerEvent()
data class RepeatModeChangedEvent(val repeatMode: Int) : PlayerEvent()
data class ShuffleModeChangedEvent(val shuffleModeEnabled: Boolean) : PlayerEvent()
data class TimelineChangedEvent(val timeline: Timeline, val manifest: Any?, val reason: Int) : PlayerEvent()
data class PlayerStateChangedEvent(val playWhenReady: Boolean, val playbackState: Int) : PlayerEvent()
