package com.tomasznajda.pulpfiction.entity

import com.tomasznajda.pulpfiction.__util.assertEquals
import org.junit.Test

class PlayerStateTest {

    @Test
    fun `isIdle returns true when state is IDLE`() {
        assertEquals(
                expected = true,
                actual = PlayerState.IDLE.isIdle)
    }

    @Test
    fun `isIdle returns false when state is not IDLE`() {
        assertEquals(
                expected = false,
                actual = PlayerState.PLAYING.isIdle)
    }

    @Test
    fun `isInitialized returns true when state is INITIALIZED`() {
        assertEquals(
                expected = true,
                actual = PlayerState.INITIALIZED.isInitialized)
    }

    @Test
    fun `isInitialized returns false when state is not IDLE`() {
        assertEquals(
                expected = false,
                actual = PlayerState.PLAYING.isInitialized)
    }

    @Test
    fun `isBuffering returns true when state is BUFFERING`() {
        assertEquals(
                expected = true,
                actual = PlayerState.BUFFERING.isBuffering)
    }

    @Test
    fun `isBuffering returns false when state is not BUFFERING`() {
        assertEquals(
                expected = false,
                actual = PlayerState.PLAYING.isBuffering)
    }

    @Test
    fun `isPlaying returns true when state is PLAYING`() {
        assertEquals(
                expected = true,
                actual = PlayerState.PLAYING.isPlaying)
    }

    @Test
    fun `isPlaying returns false when state is not PLAYING`() {
        assertEquals(
                expected = false,
                actual = PlayerState.IDLE.isPlaying)
    }

    @Test
    fun `isPaused returns true when state is PAUSED`() {
        assertEquals(
                expected = true,
                actual = PlayerState.PAUSED.isPaused)
    }

    @Test
    fun `isPaused returns false when state is not PAUSED`() {
        assertEquals(
                expected = false,
                actual = PlayerState.IDLE.isPaused)
    }

    @Test
    fun `isFinished returns true when state is FINISHED`() {
        assertEquals(
                expected = true,
                actual = PlayerState.FINISHED.isFinished)
    }

    @Test
    fun `isFinished returns false when state is not FINISHED`() {
        assertEquals(
                expected = false,
                actual = PlayerState.IDLE.isFinished)
    }

    @Test
    fun `isError returns true when state is ERROR`() {
        assertEquals(
                expected = true,
                actual = PlayerState.ERROR.isError)
    }

    @Test
    fun `isError returns false when state is not ERROR`() {
        assertEquals(
                expected = false,
                actual = PlayerState.IDLE.isError)
    }
}