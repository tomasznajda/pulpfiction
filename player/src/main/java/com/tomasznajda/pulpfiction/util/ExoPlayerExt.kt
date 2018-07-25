package com.tomasznajda.pulpfiction.util

import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.tomasznajda.pulpfiction.entity.PlayerState

val SimpleExoPlayer.pulpFictionState: PlayerState
    get() = when (playbackState) {
        Player.STATE_IDLE -> PlayerState.IDLE
        Player.STATE_BUFFERING -> PlayerState.BUFFERING
        Player.STATE_READY -> if (playWhenReady) PlayerState.PLAYING else PlayerState.PAUSED
        Player.STATE_ENDED -> PlayerState.FINISHED
        else -> throw IllegalStateException()
    }