package com.tomasznajda.pulpfiction.fullscreen

import com.tomasznajda.pulpfiction.__util.assertEquals
import com.tomasznajda.pulpfiction.exception.FullscreenDisabledException
import org.junit.Test

class FullscreenDelegateTest {

    val delegate = FullscreenDelegate()

    @Test
    fun `stateChanges emits OFF state on enable fullscreen mode`() {
        val observer = delegate.stateChanges.test()
        delegate.enable({ true }, { true })
        observer.assertValue(FullscreenState.OFF)
    }

    @Test
    fun `stateChanges emits DISABLED state on disable fullscreen mode`() {
        delegate.enable({ true }, { true })
        val observer = delegate.stateChanges.test()
        delegate.disable()
        observer.assertValue(FullscreenState.DISABLED)
    }

    @Test
    fun `stateChanges emits ON state on enter to fullscreen mode`() {
        delegate.enable({ true }, { true })
        val observer = delegate.stateChanges.test()
        delegate.enter()
        observer.assertValue(FullscreenState.ON)
    }

    @Test
    fun `stateChanges emits OFF state on exit from fullscreen mode`() {
        delegate.enable({ true }, { true })
        val observer = delegate.stateChanges.test()
        delegate.exit()
        observer.assertValue(FullscreenState.OFF)
    }

    @Test
    fun `state is DISABLED by default`() {
        assertEquals(expected = FullscreenState.DISABLED, actual = delegate.state)
    }

    @Test
    fun `enable sets state to OFF`() {
        delegate.enable({ true }, { true })
        assertEquals(expected = FullscreenState.OFF, actual = delegate.state)
    }

    @Test
    fun `disable sets state to DISABLE`() {
        delegate.enable({ true }, { true })
        delegate.disable()
        assertEquals(expected = FullscreenState.DISABLED, actual = delegate.state)
    }

    @Test(expected = FullscreenDisabledException::class)
    fun `enter throws FullscreenDisabledException when fullscreen mode is disabled`() {
        delegate.enter()
    }

    @Test(expected = FullscreenDisabledException::class)
    fun `exit throws FullscreenDisabledException when fullscreen mode is disabled`() {
        delegate.exit()
    }

    @Test
    fun `enter sets state to ON when enterFullscreenCallback returns true`() {
        delegate.enable({ true }, { true })
        delegate.enter()
        assertEquals(expected = FullscreenState.ON, actual = delegate.state)
    }

    @Test
    fun `enter does not change state when enterFullscreenCallback returns false`() {
        delegate.enable({ false }, { true })
        delegate.enter()
        assertEquals(expected = FullscreenState.OFF, actual = delegate.state)
    }

    @Test
    fun `exit sets state to OFF when exitFullscreenCallback returns true`() {
        delegate.enable({ true }, { true })
        delegate.enter()
        delegate.exit()
        assertEquals(expected = FullscreenState.OFF, actual = delegate.state)
    }

    @Test
    fun `exit does not change state when exitFullscreenCallback returns false`() {
        delegate.enable({ true }, { false })
        delegate.enter()
        delegate.exit()
        assertEquals(expected = FullscreenState.ON, actual = delegate.state)
    }

    @Test
    fun `enter invokes enterFullscreenCallback`() {
        var invocations = 0
        delegate.enable({ invocations++; true }, { true })
        assertEquals(expected = 0, actual = invocations)
        delegate.enter()
        assertEquals(expected = 1, actual = invocations)
    }

    @Test
    fun `exit invokes exitFullscreenCallback`() {
        var invocations = 0
        delegate.enable({ true }, { invocations++; true })
        assertEquals(expected = 0, actual = invocations)
        delegate.exit()
        assertEquals(expected = 1, actual = invocations)
    }
}