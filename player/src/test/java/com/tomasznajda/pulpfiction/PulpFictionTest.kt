package com.tomasznajda.pulpfiction

import android.app.Activity
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.tomasznajda.pulpfiction.__util.assertEquals
import com.tomasznajda.pulpfiction.__util.setupActivity
import com.tomasznajda.pulpfiction.entity.Clip
import com.tomasznajda.pulpfiction.entity.PlayerConfig
import com.tomasznajda.pulpfiction.entity.PlayerState
import com.tomasznajda.pulpfiction.event.analitics.AnalyticsObservable
import com.tomasznajda.pulpfiction.event.exoplayer.ExoPlayerObservable
import com.tomasznajda.pulpfiction.event.player.PlayerObservable
import com.tomasznajda.pulpfiction.event.video.VideoObservable
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class PulpFictionTest {

    val activity = setupActivity(Activity::class)
    @Mock lateinit var exoPlayer: SimpleExoPlayer
    @InjectMocks val pulpFiction = PulpFiction(activity, PlayerConfig())

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `setVideoView assign PulpFiction to given videoView when given view is not null`() {
        val videoView = mock<PfVideoView>()
        pulpFiction.videoView = videoView
        verify(videoView).pulpFiction = pulpFiction
    }

    @Test
    fun `setVideoView clearsVideoSurface in ExoPlayer when given view is null and previous view is not null`() {
        val videoView = mock<PfVideoView>()
        pulpFiction.videoView = videoView
        pulpFiction.videoView = null
        verify(exoPlayer).clearVideoSurface()
    }

    @Test
    fun `setVideoView removes PulpFiction from previous view when given view is null and previous view is not null`() {
        val videoView = mock<PfVideoView>()
        pulpFiction.videoView = videoView
        pulpFiction.videoView = null
        verify(videoView).pulpFiction = null
    }

    @Test
    fun `load prepares ExoPlayer with MediaSource created from given clip`() {
        //todo: Why ExtractorMediaSource has no equals method? :(
        pulpFiction.load(Clip("http://example.com"), true)
        verify(exoPlayer).prepare(argThat { this is ExtractorMediaSource })
    }

    @Test
    fun `load sets ExoPlayers's playWhenReady to true when autoplay is true`() {
        pulpFiction.load(Clip("http://example.com"), true)
        verify(exoPlayer).playWhenReady = true
    }

    @Test
    fun `load sets ExoPlayers's playWhenReady to false when autoplay is false`() {
        pulpFiction.load(Clip("http://example.com"), false)
        verify(exoPlayer).playWhenReady = false
    }

    @Test
    fun `play sets ExoPlayers's playWhenReady to true`() {
        pulpFiction.play()
        verify(exoPlayer).playWhenReady = true
    }

    @Test
    fun `pause sets ExoPlayers's playWhenReady to false`() {
        pulpFiction.pause()
        verify(exoPlayer).playWhenReady = false
    }

    @Test
    fun `stop invokes stop with reset on ExoPlayer`() {
        pulpFiction.stop()
        verify(exoPlayer).stop(true)
    }

    @Test
    fun `release releases ExoPlayer's resources`() {
        pulpFiction.release()
        verify(exoPlayer).release()
    }

    @Test
    fun `state returns IDLE when ExoPlayer state is IDLE`() {
        whenever(exoPlayer.playbackState).thenReturn(Player.STATE_IDLE)
        assertEquals(
                expected = PlayerState.IDLE,
                actual = pulpFiction.state)
    }

    @Test
    fun `state returns BUFFERING when ExoPlayer state is BUFFERING`() {
        whenever(exoPlayer.playbackState).thenReturn(Player.STATE_BUFFERING)
        assertEquals(
                expected = PlayerState.BUFFERING,
                actual = pulpFiction.state)
    }

    @Test
    fun `state returns PLAYING when ExoPlayer state is READY and playWhenReady is true`() {
        whenever(exoPlayer.playbackState).thenReturn(Player.STATE_READY)
        whenever(exoPlayer.playWhenReady).thenReturn(true)
        assertEquals(
                expected = PlayerState.PLAYING,
                actual = pulpFiction.state)
    }

    @Test
    fun `state returns PAUSED when ExoPlayer state is READY and playWhenReady is false`() {
        whenever(exoPlayer.playbackState).thenReturn(Player.STATE_READY)
        whenever(exoPlayer.playWhenReady).thenReturn(false)
        assertEquals(
                expected = PlayerState.PAUSED,
                actual = pulpFiction.state)
    }

    @Test
    fun `state returns FINISHED when ExoPlayer state is ENDED`() {
        whenever(exoPlayer.playbackState).thenReturn(Player.STATE_ENDED)
        assertEquals(
                expected = PlayerState.FINISHED,
                actual = pulpFiction.state)
    }

    @Test
    fun `events emits PlayerEvents from ExoPlayer`() {
        assertTrue(pulpFiction.events is PlayerObservable)
    }

    @Test
    fun `exoPlayerEvents emits ExoPlayerEvents from ExoPlayer`() {
        assertTrue(pulpFiction.exoPlayerEvents is ExoPlayerObservable)
    }

    @Test
    fun `videoEvents emits VideoEvents from ExoPlayer`() {
        assertTrue(pulpFiction.videoEvents is VideoObservable)
    }

    @Test
    fun `analyticsEvents emits AnalyticsEvents from ExoPlayer`() {
        assertTrue(pulpFiction.analyticsEvents is AnalyticsObservable)
    }
}