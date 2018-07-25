package com.tomasznajda.pulpfiction.util

import android.app.Activity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.tomasznajda.pulpfiction.BuildConfig
import com.tomasznajda.pulpfiction.__util.assertEquals
import com.tomasznajda.pulpfiction.__util.setupActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class ViewVisibilityExtKtTest {

    val activity = setupActivity(Activity::class)
    val child = TextView(activity)

    @Before
    fun setUp() {
        val root = FrameLayout(activity)
        activity.setContentView(root)
        root.addView(child)
    }

    @Test
    fun `visible sets visibility to VISIBLE`() {
        child.visibility = View.GONE
        child.visible()
        assertEquals(expected = View.VISIBLE, actual = child.visibility)
    }

    @Test
    fun `invisible sets visibility to VISIBLE`() {
        child.visibility = View.VISIBLE
        child.invisible()
        assertEquals(expected = View.INVISIBLE, actual = child.visibility)
    }

    @Test
    fun `gone sets visibility to VISIBLE`() {
        child.visibility = View.VISIBLE
        child.gone()
        assertEquals(expected = View.GONE, actual = child.visibility)
    }
}