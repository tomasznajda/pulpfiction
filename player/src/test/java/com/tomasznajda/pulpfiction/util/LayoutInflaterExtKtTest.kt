package com.tomasznajda.pulpfiction.util

import android.app.Activity
import android.widget.FrameLayout
import com.tomasznajda.pulpfiction.BuildConfig
import com.tomasznajda.pulpfiction.R
import com.tomasznajda.pulpfiction.__util.assertEquals
import com.tomasznajda.pulpfiction.__util.setupActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class LayoutInflaterExtKtTest {

    val activity = setupActivity(Activity::class)
    val root = FrameLayout(activity)

    @Before
    fun setUp() {
        activity.setContentView(root)
    }

    @Test
    fun `inflate inflates given layout`() {
        val layout = root.inflate(R.layout.pf_layer_background)
        assertEquals(expected = R.id.pfBackground, actual = layout.id)
    }

    @Test
    fun `inflate does not attach view to root by default`() {
        assertEquals(expected = 0, actual = root.childCount)
        root.inflate(R.layout.pf_layer_background)
        assertEquals(expected = 0, actual = root.childCount)
    }

    @Test
    fun `inflate attaches view to root when attachToRoot flag is true`() {
        assertEquals(expected = 0, actual = root.childCount)
        root.inflate(R.layout.pf_layer_background, attachToRoot = true)
        assertEquals(expected = 1, actual = root.childCount)
    }

    @Test
    fun `inflate does not attach view to root when attachToRoot flag is false`() {
        assertEquals(expected = 0, actual = root.childCount)
        root.inflate(R.layout.pf_layer_background, attachToRoot = false)
        assertEquals(expected = 0, actual = root.childCount)
    }
}