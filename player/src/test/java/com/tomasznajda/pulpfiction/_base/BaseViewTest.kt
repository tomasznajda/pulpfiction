package com.tomasznajda.pulpfiction._base

import android.app.Activity
import android.content.Context
import android.widget.FrameLayout
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.tomasznajda.pulpfiction.BuildConfig
import com.tomasznajda.pulpfiction.__util.setupActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class BaseViewTest {

    val activity = setupActivity(Activity::class)
    val root = FrameLayout(activity)

    interface TestView
    class TestViewImpl(override val presenter: BasePresenter<TestView>, context: Context)
        : BaseView<TestView>(context), TestView

    val presenter = mock<BasePresenter<TestView>>()
    val view = TestViewImpl(presenter, activity)

    @Before
    fun setUp() {
        activity.setContentView(root)
    }

    @Test
    fun `onAttachedToWindow attaches view to presenter`() {
        root.addView(view)
        verify(presenter).attach(view)
    }

    @Test
    fun `onDetachedFromWindow detaches view from presenter`() {
        root.addView(view)
        root.removeAllViews()
        verify(presenter).detach()
    }
}