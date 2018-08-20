package com.tomasznajda.pulpfiction.entity

import android.view.View.GONE
import android.view.View.VISIBLE
import com.tomasznajda.pulpfiction.fullscreen.FullscreenState


data class ControlsViewState(private val playerState: PlayerState,
                             private val fullscreenState: FullscreenState) {

    val playVisibility: Int
        get() = if (playerState.let { it.isPaused or it.isInitialized }) VISIBLE else GONE

    val pauseVisibility: Int
        get() = if (playerState.isPlaying) VISIBLE else GONE

    val progressVisibility: Int
        get() = if (playerState.isBuffering) VISIBLE else GONE

    val enterFullscreenVisibility: Int
        get() = if (fullscreenState.isOff) VISIBLE else GONE

    val exitFullscreenVisibility: Int
        get() = if (fullscreenState.isOn) VISIBLE else GONE
}