package com.tomasznajda.pulpfiction.fullscreen

import com.tomasznajda.pulpfiction.__util.assertEquals
import org.junit.Test

class FullscreenStateTest {

    @Test
    fun `isOn returns true when state is ON`() {
        assertEquals(
                expected = true,
                actual = FullscreenState.ON.isOn)
    }

    @Test
    fun `isOn returns false when state is not ON`() {
        assertEquals(
                expected = false,
                actual = FullscreenState.DISABLED.isOn)
    }

    @Test
    fun `isOff returns true when state is OFF`() {
        assertEquals(
                expected = true,
                actual = FullscreenState.OFF.isOff)
    }

    @Test
    fun `isOff returns false when state is not OFF`() {
        assertEquals(
                expected = false,
                actual = FullscreenState.ON.isOff)
    }

    @Test
    fun `isDisabled returns true when state is DISABLED`() {
        assertEquals(
                expected = true,
                actual = FullscreenState.DISABLED.isDisabled)
    }

    @Test
    fun `isDisabled returns false when state is not DISABLED`() {
        assertEquals(
                expected = false,
                actual = FullscreenState.ON.isDisabled)
    }
}