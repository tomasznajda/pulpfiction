package com.tomasznajda.pulpfiction.entity

import android.view.View.GONE
import android.view.View.VISIBLE


data class ControlsViewState(private val playerState: PlayerState) {

    val playVisibility: Int
        get() = if (playerState.let { it.isPaused or it.isInitialized }) VISIBLE else GONE

    val pauseVisibility: Int
        get() = if (playerState.isPlaying) VISIBLE else GONE

    val progressVisibility: Int
        get() = if (playerState.isBuffering) VISIBLE else GONE
}