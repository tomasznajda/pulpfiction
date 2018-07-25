package com.tomasznajda.pulpfiction.event.player

import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.tomasznajda.pulpfiction.entity.PlayerState
import com.tomasznajda.pulpfiction.event.analitics.AnalyticsEvent
import com.tomasznajda.pulpfiction.event.analitics.AnalyticsObservable
import com.tomasznajda.pulpfiction.event.analitics.PlayerStateChangedEvent
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.functions.Function

class PlayerObservable(exoPlayer: SimpleExoPlayer,
                       private var analytics: Observable<AnalyticsEvent> = AnalyticsObservable(exoPlayer))
    : Observable<PlayerEvent>() {

    override fun subscribeActual(observer: Observer<in PlayerEvent>) {
        analytics
                .filter { it is PlayerStateChangedEvent }
                .map { it as PlayerStateChangedEvent }
                .map { createPlayerInfo(it.playbackState, it.playWhenReady) }
                .onErrorResumeNext(Function { Observable.empty() })
                .subscribe(observer)
    }

    private fun createPlayerInfo(playbackState: Int, playWhenReady: Boolean) =
            when (playbackState) {
                Player.STATE_IDLE -> PlayerInfo(PlayerState.IDLE)
                Player.STATE_BUFFERING -> PlayerInfo(PlayerState.BUFFERING)
                Player.STATE_READY -> PlayerInfo(if (playWhenReady) PlayerState.PLAYING else PlayerState.PAUSED)
                Player.STATE_ENDED -> PlayerInfo(PlayerState.FINISHED)
                else -> throw IllegalArgumentException()
            }
}