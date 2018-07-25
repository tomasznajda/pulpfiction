package com.tomasznajda.pulpfiction.event.player

import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.analytics.AnalyticsListener
import com.nhaarman.mockitokotlin2.mock
import com.tomasznajda.pulpfiction.entity.PlayerState
import com.tomasznajda.pulpfiction.event.analitics.AnalyticsEvent
import com.tomasznajda.pulpfiction.event.analitics.PlayerStateChangedEvent
import io.reactivex.subjects.PublishSubject
import org.junit.Test

class PlayerObservableTest {

    val exoPlayer = mock<SimpleExoPlayer>()
    val analytics = PublishSubject.create<AnalyticsEvent>()
    val observable = PlayerObservable(exoPlayer, analytics)
    val observer = observable.test()
    val time = mock<AnalyticsListener.EventTime>()

    @Test
    fun `onPlayerStateChanged emits IDLE when state is IDLE`() {
        analytics.onNext(PlayerStateChangedEvent(time, false, Player.STATE_IDLE))
        observer.assertValue(PlayerInfo(PlayerState.IDLE))
    }

    @Test
    fun `onPlayerStateChanged emits BUFFERING when state is BUFFERING`() {
        analytics.onNext(PlayerStateChangedEvent(time, false, Player.STATE_BUFFERING))
        observer.assertValue(PlayerInfo(PlayerState.BUFFERING))
    }

    @Test
    fun `onPlayerStateChanged emits PAUSED when state is READY and playWhenReady is false`() {
        analytics.onNext(PlayerStateChangedEvent(time, false, Player.STATE_READY))
        observer.assertValue(PlayerInfo(PlayerState.PAUSED))
    }

    @Test
    fun `onPlayerStateChanged emits PLAYING when state is READY and playWhenReady is true`() {
        analytics.onNext(PlayerStateChangedEvent(time, true, Player.STATE_READY))
        observer.assertValue(PlayerInfo(PlayerState.PLAYING))
    }

    @Test
    fun `onPlayerStateChanged emits FINISHED when state is ENDED`() {
        analytics.onNext(PlayerStateChangedEvent(time, false, Player.STATE_ENDED))
        observer.assertValue(PlayerInfo(PlayerState.FINISHED))
    }

    @Test
    fun `onPlayerStateChanged does not emit any event when state is unknown`() {
        analytics.onNext(PlayerStateChangedEvent(time, false, 99))
        observer.assertNoValues()
    }

}