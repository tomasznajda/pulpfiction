package com.tomasznajda.pulpfiction.controls

import android.app.Activity
import android.view.View
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.tomasznajda.pulpfiction.BuildConfig
import com.tomasznajda.pulpfiction.PulpFiction
import com.tomasznajda.pulpfiction.__util.assertEquals
import com.tomasznajda.pulpfiction.__util.setupActivity
import com.tomasznajda.pulpfiction.entity.ControlsViewState
import kotlinx.android.synthetic.main.pf_view_controls.view.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class ControlsViewTest {

    val activity = setupActivity(Activity::class)
    val view = ControlsView(activity)

    @Before
    fun setUp() {
        activity.setContentView(view)
    }

    @Test
    fun `playClicks emits event on pfPlay button clicks`() {
        val observer = view.playClicks.test()
        view.pfPlay.performClick()
        observer.assertValue(Unit)
    }

    @Test
    fun `pauseClicks emits event on pfPause button clicks`() {
        val observer = view.pauseClicks.test()
        view.pfPause.performClick()
        observer.assertValue(Unit)
    }

    @Test
    fun `play invokes play on PulpFiction`() {
        val pulpFiction = mock<PulpFiction>()
        view.init(pulpFiction, mock())
        view.play()
        verify(pulpFiction).play()
    }

    @Test
    fun `pause invokes pause on PulpFiction`() {
        val pulpFiction = mock<PulpFiction>()
        view.init(pulpFiction, mock())
        view.pause()
        verify(pulpFiction).pause()
    }

    @Test
    fun `render changes play button visibility to VISIBLE when playVisibility is VISIBLE`() {
        val state = mock<ControlsViewState> {
            on { playVisibility }.thenReturn(View.VISIBLE)
            on { pauseVisibility }.thenReturn(View.GONE)
            on { progressVisibility }.thenReturn(View.GONE)
        }
        view.pfPlay.visibility = View.INVISIBLE
        view.render(state)
        assertEquals(expected = View.VISIBLE, actual = view.pfPlay.visibility)
    }

    @Test
    fun `render changes play button visibility to GONE when playVisibility is GONE`() {
        val state = mock<ControlsViewState> {
            on { playVisibility }.thenReturn(View.GONE)
            on { pauseVisibility }.thenReturn(View.GONE)
            on { progressVisibility }.thenReturn(View.GONE)
        }
        view.pfPlay.visibility = View.INVISIBLE
        view.render(state)
        assertEquals(expected = View.GONE, actual = view.pfPlay.visibility)
    }

    @Test
    fun `render changes pause button visibility to VISIBLE when pauseVisibility is VISIBLE`() {
        val state = mock<ControlsViewState> {
            on { playVisibility }.thenReturn(View.GONE)
            on { pauseVisibility }.thenReturn(View.VISIBLE)
            on { progressVisibility }.thenReturn(View.GONE)
        }
        view.pfPlay.visibility = View.INVISIBLE
        view.render(state)
        assertEquals(expected = View.VISIBLE, actual = view.pfPause.visibility)
    }

    @Test
    fun `render changes pause button visibility to GONE when pauseVisibility is GONE`() {
        val state = mock<ControlsViewState> {
            on { playVisibility }.thenReturn(View.GONE)
            on { pauseVisibility }.thenReturn(View.GONE)
            on { progressVisibility }.thenReturn(View.GONE)
        }
        view.pfPause.visibility = View.INVISIBLE
        view.render(state)
        assertEquals(expected = View.GONE, actual = view.pfPause.visibility)
    }

    @Test
    fun `render changes progress visibility to VISIBLE when progressVisibility is VISIBLE`() {
        val state = mock<ControlsViewState> {
            on { playVisibility }.thenReturn(View.GONE)
            on { pauseVisibility }.thenReturn(View.GONE)
            on { progressVisibility }.thenReturn(View.VISIBLE)
        }
        view.pfProgress.visibility = View.INVISIBLE
        view.render(state)
        assertEquals(expected = View.VISIBLE, actual = view.pfProgress.visibility)
    }

    @Test
    fun `render changes progress visibility to GONE when progressVisibility is GONE`() {
        val state = mock<ControlsViewState> {
            on { playVisibility }.thenReturn(View.GONE)
            on { pauseVisibility }.thenReturn(View.GONE)
            on { progressVisibility }.thenReturn(View.GONE)
        }
        view.pfProgress.visibility = View.INVISIBLE
        view.render(state)
        assertEquals(expected = View.GONE, actual = view.pfProgress.visibility)
    }
}