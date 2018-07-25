package com.tomasznajda.pulpfiction.event.exoplayer

import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.subjects.PublishSubject

class ExoPlayerObservable(private val exoPlayer: SimpleExoPlayer)
    : Observable<PlayerEvent>(), Player.EventListener {

    private val subject = PublishSubject.create<PlayerEvent>()

    override fun subscribeActual(observer: Observer<in PlayerEvent>) =
            subject
                    .doOnSubscribe { exoPlayer.addListener(this) }
                    .doOnDispose { exoPlayer.removeListener(this) }
                    .subscribe(observer)

    override fun onSeekProcessed() =
            subject.onNext(SeekProcessedEvent())

    override fun onPlayerError(error: ExoPlaybackException) =
            subject.onNext(PlayerErrorEvent(error))

    override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) =
            subject.onNext(PlaybackParametersChangedEvent(playbackParameters))

    override fun onTracksChanged(trackGroups: TrackGroupArray, trackSelections: TrackSelectionArray) =
            subject.onNext(TracksChangedEvent(trackGroups, trackSelections))

    override fun onLoadingChanged(isLoading: Boolean) =
            subject.onNext(LoadingChangedEvent(isLoading))

    override fun onPositionDiscontinuity(reason: Int) =
            subject.onNext(PositionDiscontinuityEvent(reason))

    override fun onRepeatModeChanged(repeatMode: Int) =
            subject.onNext(RepeatModeChangedEvent(repeatMode))

    override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) =
            subject.onNext(ShuffleModeChangedEvent(shuffleModeEnabled))

    override fun onTimelineChanged(timeline: Timeline, manifest: Any?, reason: Int) =
            subject.onNext(TimelineChangedEvent(timeline, manifest, reason))

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) =
            subject.onNext(PlayerStateChangedEvent(playWhenReady, playbackState))

}