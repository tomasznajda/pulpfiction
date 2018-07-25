package com.tomasznajda.pulpfiction

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import com.google.android.exoplayer2.SimpleExoPlayer
import com.nhaarman.mockitokotlin2.mock
import com.tomasznajda.pulpfiction.__util.assertEquals
import com.tomasznajda.pulpfiction.__util.assertNotEquals
import com.tomasznajda.pulpfiction.__util.setupActivity
import com.tomasznajda.pulpfiction.entity.PlayerState
import com.tomasznajda.pulpfiction.event.player.PlayerEvent
import com.tomasznajda.pulpfiction.event.player.PlayerInfo
import com.tomasznajda.pulpfiction.util.gone
import com.tomasznajda.pulpfiction.util.visible
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.pf_layer_background.view.*
import kotlinx.android.synthetic.main.pf_layer_controls.view.*
import kotlinx.android.synthetic.main.pf_layer_player.view.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class PfVideoViewTest {

    val activity = setupActivity(Activity::class)
    val videoView = PfVideoView(activity)

    @Test
    fun `init inflates background layout`() {
        assertNotEquals(unexpected = null, actual = videoView.pfBackground)
    }

    @Test
    fun `init inflates player layout`() {
        assertNotEquals(unexpected = null, actual = videoView.pfPlayerView)
    }

    @Test
    fun `init inflates controls layout`() {
        assertNotEquals(unexpected = null, actual = videoView.pfControlsView)
    }

    @Test
    fun `init inflates layouts in expected order`() {
        assertEquals(expected = R.id.pfBackground, actual = videoView.getChildAt(0).id)
        assertEquals(expected = R.id.pfPlayerView, actual = videoView.getChildAt(1).id)
        assertEquals(expected = R.id.pfControlsView, actual = videoView.getChildAt(2).id)
    }

    @Test
    fun `init disables build-in controls`() {
        assertEquals(expected = false, actual = videoView.pfPlayerView.useController)
    }

    @Test
    fun `setShutterBackgroundColor invokes setShutterBackgroundColor on player view`() {
        videoView.setShutterBackgroundColor(Color.RED)
        val shutterView = videoView.pfPlayerView.findViewById<View>(com.google.android.exoplayer2.ui.R.id.exo_shutter)
        assertEquals(
                expected = Color.RED,
                actual = (shutterView.background as ColorDrawable).color)
    }

    @Test
    fun `setPlaceholderImage sets image background`() {
        videoView.setPlaceholderImage(R.drawable.logo)
        assertEquals(
                expected = R.drawable.logo,
                actual = Shadows.shadowOf(videoView.pfBackground.drawable).createdFromResId)
    }

    @Test
    fun `render shows background when state is IDLE`() {
        val events = PublishSubject.create<PlayerEvent>()
        with(videoView) {
            pulpFiction = mock { on { this.events }.thenReturn(events) }
            pfBackground.gone()
            events.onNext(PlayerInfo(PlayerState.IDLE))
            assertEquals(expected = View.VISIBLE, actual = pfBackground.visibility)
        }
    }

    @Test
    fun `render hides player when state is IDLE`() {
        val events = PublishSubject.create<PlayerEvent>()
        with(videoView) {
            pulpFiction = mock { on { this.events }.thenReturn(events) }
            pfPlayerView.visible()
            events.onNext(PlayerInfo(PlayerState.IDLE))
            assertEquals(expected = View.GONE, actual = pfPlayerView.visibility)
        }
    }

    @Test
    fun `render hides controls when state is IDLE`() {
        val events = PublishSubject.create<PlayerEvent>()
        with(videoView) {
            pulpFiction = mock { on { this.events }.thenReturn(events) }
            pfControlsView.visible()
            events.onNext(PlayerInfo(PlayerState.IDLE))
            assertEquals(expected = View.GONE, actual = pfControlsView.visibility)
        }
    }

    @Test
    fun `render shows background when state is FINISHED`() {
        val events = PublishSubject.create<PlayerEvent>()
        with(videoView) {
            pulpFiction = mock { on { this.events }.thenReturn(events) }
            pfBackground.gone()
            events.onNext(PlayerInfo(PlayerState.FINISHED))
            assertEquals(expected = View.VISIBLE, actual = pfBackground.visibility)
        }
    }

    @Test
    fun `render hides player when state is FINISHED`() {
        val events = PublishSubject.create<PlayerEvent>()
        with(videoView) {
            pulpFiction = mock { on { this.events }.thenReturn(events) }
            pfPlayerView.visible()
            events.onNext(PlayerInfo(PlayerState.FINISHED))
            assertEquals(expected = View.GONE, actual = pfPlayerView.visibility)
        }
    }

    @Test
    fun `render hides controls when state is FINISHED`() {
        val events = PublishSubject.create<PlayerEvent>()
        with(videoView) {
            pulpFiction = mock { on { this.events }.thenReturn(events) }
            pfControlsView.visible()
            events.onNext(PlayerInfo(PlayerState.FINISHED))
            assertEquals(expected = View.GONE, actual = pfControlsView.visibility)
        }
    }

    @Test
    fun `render hides background when state is PLAYING`() {
        val events = PublishSubject.create<PlayerEvent>()
        with(videoView) {
            pulpFiction = mock { on { this.events }.thenReturn(events) }
            pfBackground.visible()
            events.onNext(PlayerInfo(PlayerState.PLAYING))
            assertEquals(expected = View.GONE, actual = pfBackground.visibility)
        }
    }

    @Test
    fun `render hides background when state is PAUSED`() {
        val events = PublishSubject.create<PlayerEvent>()
        with(videoView) {
            pulpFiction = mock { on { this.events }.thenReturn(events) }
            pfBackground.visible()
            events.onNext(PlayerInfo(PlayerState.PAUSED))
            assertEquals(expected = View.GONE, actual = pfBackground.visibility)
        }
    }

    @Test
    fun `render hides background when state is BUFFERING`() {
        val events = PublishSubject.create<PlayerEvent>()
        with(videoView) {
            pulpFiction = mock { on { this.events }.thenReturn(events) }
            pfBackground.visible()
            events.onNext(PlayerInfo(PlayerState.BUFFERING))
            assertEquals(expected = View.GONE, actual = pfBackground.visibility)
        }
    }

    @Test
    fun `render shows player when state is PLAYING`() {
        val events = PublishSubject.create<PlayerEvent>()
        with(videoView) {
            pulpFiction = mock { on { this.events }.thenReturn(events) }
            pfPlayerView.gone()
            events.onNext(PlayerInfo(PlayerState.PLAYING))
            assertEquals(expected = View.VISIBLE, actual = pfPlayerView.visibility)
        }
    }

    @Test
    fun `render shows player when state is PAUSED`() {
        val events = PublishSubject.create<PlayerEvent>()
        with(videoView) {
            pulpFiction = mock { on { this.events }.thenReturn(events) }
            pfPlayerView.gone()
            events.onNext(PlayerInfo(PlayerState.PAUSED))
            assertEquals(expected = View.VISIBLE, actual = pfPlayerView.visibility)
        }
    }

    @Test
    fun `render shows player when state is BUFFERING`() {
        val events = PublishSubject.create<PlayerEvent>()
        with(videoView) {
            pulpFiction = mock { on { this.events }.thenReturn(events) }
            pfPlayerView.gone()
            events.onNext(PlayerInfo(PlayerState.BUFFERING))
            assertEquals(expected = View.VISIBLE, actual = pfPlayerView.visibility)
        }
    }

    @Test
    fun `render shows controls when state is PLAYING`() {
        val events = PublishSubject.create<PlayerEvent>()
        with(videoView) {
            pulpFiction = mock { on { this.events }.thenReturn(events) }
            pfControlsView.gone()
            events.onNext(PlayerInfo(PlayerState.PLAYING))
            assertEquals(expected = View.VISIBLE, actual = pfControlsView.visibility)
        }
    }

    @Test
    fun `render shows controls when state is PAUSED`() {
        val events = PublishSubject.create<PlayerEvent>()
        with(videoView) {
            pulpFiction = mock { on { this.events }.thenReturn(events) }
            pfControlsView.gone()
            events.onNext(PlayerInfo(PlayerState.PAUSED))
            assertEquals(expected = View.VISIBLE, actual = pfControlsView.visibility)
        }
    }

    @Test
    fun `render shows controls when state is BUFFERING`() {
        val events = PublishSubject.create<PlayerEvent>()
        with(videoView) {
            pulpFiction = mock { on { this.events }.thenReturn(events) }
            pfControlsView.gone()
            events.onNext(PlayerInfo(PlayerState.BUFFERING))
            assertEquals(expected = View.VISIBLE, actual = pfControlsView.visibility)
        }
    }
}