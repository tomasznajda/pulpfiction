package com.tomasznajda.pulpfiction.entity

import android.view.View
import com.tomasznajda.pulpfiction.__util.assertEquals
import com.tomasznajda.pulpfiction.fullscreen.FullscreenState
import org.junit.Test

class ControlsViewStateTest {

    val state = ControlsViewState(
            playerState = PlayerState.IDLE,
            fullscreenState = FullscreenState.DISABLED)

    @Test
    fun `getPlayVisibility returns VISIBLE when state is INITIALIZED`() {
        assertEquals(
                expected = View.VISIBLE,
                actual = state.copy(playerState = PlayerState.INITIALIZED).playVisibility)
    }

    @Test
    fun `getPlayVisibility returns VISIBLE when state is PAUSED`() {
        assertEquals(
                expected = View.VISIBLE,
                actual = state.copy(playerState = PlayerState.PAUSED).playVisibility)
    }

    @Test
    fun `getPlayVisibility returns GONE when state is IDLE`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(playerState = PlayerState.IDLE).playVisibility)
    }

    @Test
    fun `getPlayVisibility returns GONE when state is BUFFERING`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(playerState = PlayerState.BUFFERING).playVisibility)
    }

    @Test
    fun `getPlayVisibility returns GONE when state is PLAYING`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(playerState = PlayerState.PLAYING).playVisibility)
    }

    @Test
    fun `getPlayVisibility returns GONE when state is FINISHED`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(playerState = PlayerState.FINISHED).playVisibility)
    }

    @Test
    fun `getPlayVisibility returns GONE when state is ERROR`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(playerState = PlayerState.ERROR).playVisibility)
    }

    @Test
    fun `getPauseVisibility returns VISIBLE when state is PLAYING`() {
        assertEquals(
                expected = View.VISIBLE,
                actual = state.copy(playerState = PlayerState.PLAYING).pauseVisibility)
    }

    @Test
    fun `getPauseVisibility returns GONE when state is PAUSED`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(playerState = PlayerState.PAUSED).pauseVisibility)
    }

    @Test
    fun `getPauseVisibility returns GONE when state is IDLE`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(playerState = PlayerState.IDLE).pauseVisibility)
    }

    @Test
    fun `getPauseVisibility returns GONE when state is BUFFERING`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(playerState = PlayerState.BUFFERING).pauseVisibility)
    }

    @Test
    fun `getPauseVisibility returns GONE when state is INITIALIZED`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(playerState = PlayerState.INITIALIZED).pauseVisibility)
    }

    @Test
    fun `getPauseVisibility returns GONE when state is FINISHED`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(playerState = PlayerState.FINISHED).pauseVisibility)
    }

    @Test
    fun `getPauseVisibility returns GONE when state is ERROR`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(playerState = PlayerState.ERROR).pauseVisibility)
    }

    @Test
    fun `getProgressVisibility returns VISIBLE when state is BUFFERING`() {
        assertEquals(
                expected = View.VISIBLE,
                actual = state.copy(playerState = PlayerState.BUFFERING).progressVisibility)
    }

    @Test
    fun `getProgressVisibility returns GONE when state is IDLE`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(playerState = PlayerState.IDLE).progressVisibility)
    }

    @Test
    fun `getProgressVisibility returns GONE when state is INITIALIZED`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(playerState = PlayerState.INITIALIZED).progressVisibility)
    }

    @Test
    fun `getProgressVisibility returns GONE when state is PLAYING`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(playerState = PlayerState.PLAYING).progressVisibility)
    }

    @Test
    fun `getProgressVisibility returns GONE when state is PAUSED`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(playerState = PlayerState.PAUSED).progressVisibility)
    }

    @Test
    fun `getProgressVisibility returns GONE when state is FINISHED`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(playerState = PlayerState.FINISHED).progressVisibility)
    }

    @Test
    fun `getProgressVisibility returns GONE when state is ERROR`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(playerState = PlayerState.ERROR).progressVisibility)
    }

    @Test
    fun `enterFullscreenVisibility returns VISIBLE when fullscreenState is OFF`() {
        assertEquals(
                expected = View.VISIBLE,
                actual = state.copy(fullscreenState = FullscreenState.OFF).enterFullscreenVisibility)
    }

    @Test
    fun `enterFullscreenVisibility returns GONE when fullscreenState is ON`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(fullscreenState = FullscreenState.ON).enterFullscreenVisibility)
    }

    @Test
    fun `enterFullscreenVisibility returns GONE when fullscreenState is DISABLED`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(fullscreenState = FullscreenState.DISABLED).enterFullscreenVisibility)
    }

    @Test
    fun `exitFullscreenVisibility returns VISIBLE when fullscreenState is ON`() {
        assertEquals(
                expected = View.VISIBLE,
                actual = state.copy(fullscreenState = FullscreenState.ON).exitFullscreenVisibility)
    }

    @Test
    fun `exitFullscreenVisibility returns GONE when fullscreenState is OFF`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(fullscreenState = FullscreenState.OFF).exitFullscreenVisibility)
    }

    @Test
    fun `exitFullscreenVisibility returns GONE when fullscreenState is DISABLED`() {
        assertEquals(
                expected = View.GONE,
                actual = state.copy(fullscreenState = FullscreenState.DISABLED).exitFullscreenVisibility)
    }
}