package com.tomasznajda.pulpfiction.entity

import android.view.View
import com.tomasznajda.pulpfiction.__util.assertEquals
import org.junit.Test

class ControlsViewStateTest {

    @Test
    fun `getPlayVisibility returns VISIBLE when state is INITIALIZED`() {
        assertEquals(
                expected = View.VISIBLE,
                actual = ControlsViewState(PlayerState.INITIALIZED).playVisibility)
    }

    @Test
    fun `getPlayVisibility returns VISIBLE when state is PAUSED`() {
        assertEquals(
                expected = View.VISIBLE,
                actual = ControlsViewState(PlayerState.PAUSED).playVisibility)
    }

    @Test
    fun `getPlayVisibility returns GONE when state is IDLE`() {
        assertEquals(
                expected = View.GONE,
                actual = ControlsViewState(PlayerState.IDLE).playVisibility)
    }

    @Test
    fun `getPlayVisibility returns GONE when state is BUFFERING`() {
        assertEquals(
                expected = View.GONE,
                actual = ControlsViewState(PlayerState.BUFFERING).playVisibility)
    }

    @Test
    fun `getPlayVisibility returns GONE when state is PLAYING`() {
        assertEquals(
                expected = View.GONE,
                actual = ControlsViewState(PlayerState.PLAYING).playVisibility)
    }

    @Test
    fun `getPlayVisibility returns GONE when state is FINISHED`() {
        assertEquals(
                expected = View.GONE,
                actual = ControlsViewState(PlayerState.FINISHED).playVisibility)
    }

    @Test
    fun `getPlayVisibility returns GONE when state is ERROR`() {
        assertEquals(
                expected = View.GONE,
                actual = ControlsViewState(PlayerState.ERROR).playVisibility)
    }

    @Test
    fun `getPauseVisibility returns VISIBLE when state is PLAYING`() {
        assertEquals(
                expected = View.VISIBLE,
                actual = ControlsViewState(PlayerState.PLAYING).pauseVisibility)
    }

    @Test
    fun `getPauseVisibility returns GONE when state is PAUSED`() {
        assertEquals(
                expected = View.GONE,
                actual = ControlsViewState(PlayerState.PAUSED).pauseVisibility)
    }

    @Test
    fun `getPauseVisibility returns GONE when state is IDLE`() {
        assertEquals(
                expected = View.GONE,
                actual = ControlsViewState(PlayerState.IDLE).pauseVisibility)
    }

    @Test
    fun `getPauseVisibility returns GONE when state is BUFFERING`() {
        assertEquals(
                expected = View.GONE,
                actual = ControlsViewState(PlayerState.BUFFERING).pauseVisibility)
    }

    @Test
    fun `getPauseVisibility returns GONE when state is INITIALIZED`() {
        assertEquals(
                expected = View.GONE,
                actual = ControlsViewState(PlayerState.INITIALIZED).pauseVisibility)
    }

    @Test
    fun `getPauseVisibility returns GONE when state is FINISHED`() {
        assertEquals(
                expected = View.GONE,
                actual = ControlsViewState(PlayerState.FINISHED).pauseVisibility)
    }

    @Test
    fun `getPauseVisibility returns GONE when state is ERROR`() {
        assertEquals(
                expected = View.GONE,
                actual = ControlsViewState(PlayerState.ERROR).pauseVisibility)
    }

    @Test
    fun `getProgressVisibility returns VISIBLE when state is BUFFERING`() {
        assertEquals(
                expected = View.VISIBLE,
                actual = ControlsViewState(PlayerState.BUFFERING).progressVisibility)
    }

    @Test
    fun `getProgressVisibility returns GONE when state is IDLE`() {
        assertEquals(
                expected = View.GONE,
                actual = ControlsViewState(PlayerState.IDLE).progressVisibility)
    }

    @Test
    fun `getProgressVisibility returns GONE when state is INITIALIZED`() {
        assertEquals(
                expected = View.GONE,
                actual = ControlsViewState(PlayerState.INITIALIZED).progressVisibility)
    }

    @Test
    fun `getProgressVisibility returns GONE when state is PLAYING`() {
        assertEquals(
                expected = View.GONE,
                actual = ControlsViewState(PlayerState.PLAYING).progressVisibility)
    }

    @Test
    fun `getProgressVisibility returns GONE when state is PAUSED`() {
        assertEquals(
                expected = View.GONE,
                actual = ControlsViewState(PlayerState.PAUSED).progressVisibility)
    }

    @Test
    fun `getProgressVisibility returns GONE when state is FINISHED`() {
        assertEquals(
                expected = View.GONE,
                actual = ControlsViewState(PlayerState.FINISHED).progressVisibility)
    }

    @Test
    fun `getProgressVisibility returns GONE when state is ERROR`() {
        assertEquals(
                expected = View.GONE,
                actual = ControlsViewState(PlayerState.ERROR).progressVisibility)
    }
}