package com.tomasznajda.pulpfiction.controls

import android.content.Context
import android.util.AttributeSet
import com.jakewharton.rxbinding2.view.clicks
import com.tomasznajda.pulpfiction.PfVideoView
import com.tomasznajda.pulpfiction.PulpFiction
import com.tomasznajda.pulpfiction.R
import com.tomasznajda.pulpfiction._base.BaseView
import com.tomasznajda.pulpfiction.entity.ControlsViewState
import com.tomasznajda.pulpfiction.util.inflate
import kotlinx.android.synthetic.main.pf_view_controls.view.*

class ControlsView : BaseView<ControlsContract.View>, ControlsContract.View {

    override val playClicks by lazy { pfPlay.clicks() }

    override val pauseClicks by lazy { pfPause.clicks() }

    override val enterFullscreenClicks by lazy { pfFullscreenEnter.clicks() }

    override val exitFullscreenClicks by lazy { pfFullscreenExit.clicks() }

    override val presenter = ControlsPresenter()

    private var pulpFiction: PulpFiction? = null

    private var videoView: PfVideoView? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        inflate(R.layout.pf_view_controls, attachToRoot = true)
    }

    internal fun init(pulpFiction: PulpFiction, videoView: PfVideoView) {
        this.pulpFiction = pulpFiction
        this.videoView = videoView
    }

    internal fun deinit() {
        pulpFiction = null
        videoView = null
    }

    override fun render(state: ControlsViewState) {
        pfPlay.visibility = state.playVisibility
        pfPause.visibility = state.pauseVisibility
        pfProgress.visibility = state.progressVisibility
        pfFullscreenEnter.visibility = state.enterFullscreenVisibility
        pfFullscreenExit.visibility = state.exitFullscreenVisibility
    }

    override fun play() {
        pulpFiction?.play()
    }

    override fun pause() {
        pulpFiction?.pause()
    }

    override fun enterFullscreen() {
        pulpFiction?.enterFullscreen()
    }

    override fun exitFullscreen() {
        pulpFiction?.exitFullscreen()
    }
}