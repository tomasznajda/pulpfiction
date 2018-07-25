package com.tomasznajda.pulpfiction.util

import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.nhaarman.mockitokotlin2.mock
import com.tomasznajda.pulpfiction.__util.assertEquals
import com.tomasznajda.pulpfiction.entity.PlayerState
import org.junit.Test

class ExoPlayerExtKtTest {

    @Test
    fun `getPulpFictionState returns IDLE when playback state is IDLE`() {
        val exoPlayer = mock<SimpleExoPlayer> { on { playbackState }.thenReturn(Player.STATE_IDLE) }
        assertEquals(expected = PlayerState.IDLE, actual = exoPlayer.pulpFictionState)
    }

    @Test
    fun `getPulpFictionState returns BUFFERING when playback state is BUFFERING`() {
        val exoPlayer = mock<SimpleExoPlayer> { on { playbackState }.thenReturn(Player.STATE_BUFFERING) }
        assertEquals(expected = PlayerState.BUFFERING, actual = exoPlayer.pulpFictionState)
    }

    @Test
    fun `getPulpFictionState returns PLAYING when playback state is READY and playWhenReady is true`() {
        val exoPlayer = mock<SimpleExoPlayer> {
            on { playbackState }.thenReturn(Player.STATE_READY)
            on { playWhenReady }.thenReturn(true)
        }
        assertEquals(expected = PlayerState.PLAYING, actual = exoPlayer.pulpFictionState)
    }

    @Test
    fun `getPulpFictionState returns PAUSED when playback state is READY and playWhenReady is false`() {
        val exoPlayer = mock<SimpleExoPlayer> {
            on { playbackState }.thenReturn(Player.STATE_READY)
            on { playWhenReady }.thenReturn(false)
        }
        assertEquals(expected = PlayerState.PAUSED, actual = exoPlayer.pulpFictionState)
    }

    @Test
    fun `getPulpFictionState returns FINISHED when playback state is ENDED`() {
        val exoPlayer = mock<SimpleExoPlayer> { on { playbackState }.thenReturn(Player.STATE_ENDED) }
        assertEquals(expected = PlayerState.FINISHED, actual = exoPlayer.pulpFictionState)
    }
}