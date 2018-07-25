package com.tomasznajda.pulpfiction

import android.content.Context
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.util.AttributeSet
import android.widget.FrameLayout
import com.tomasznajda.pulpfiction.entity.ControlsViewState
import com.tomasznajda.pulpfiction.entity.PlayerState
import com.tomasznajda.pulpfiction.event.player.PlayerInfo
import com.tomasznajda.pulpfiction.util.gone
import com.tomasznajda.pulpfiction.util.inflate
import com.tomasznajda.pulpfiction.util.visible
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.pf_layer_background.view.*
import kotlinx.android.synthetic.main.pf_layer_controls.view.*
import kotlinx.android.synthetic.main.pf_layer_player.view.*

class PfVideoView : FrameLayout {

    var pulpFiction: PulpFiction? = null
        set(value) {
            field = value
            pfPlayerView.player = value?.exoPlayer
            if (value != null) init(value) else deinit()
        }

    private val disposables = CompositeDisposable()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        inflate(R.layout.pf_layer_background, attachToRoot = true)
        inflate(R.layout.pf_layer_player, attachToRoot = true)
        inflate(R.layout.pf_layer_controls, attachToRoot = true)
        pfPlayerView.useController = false
    }

    fun setShutterBackgroundColor(@ColorInt color: Int) = pfPlayerView.setShutterBackgroundColor(color)

    fun setPlaceholderImage(@DrawableRes resId: Int?) = pfBackground.setImageResource(resId ?: 0)

    private fun render(state: PlayerState) =
            when (state) {
                PlayerState.IDLE, PlayerState.FINISHED -> showPlaceholder()
                else -> showPlayer()
            }

    private fun showPlaceholder() {
        pfBackground.visible()
        pfPlayerView.gone()
        pfControlsView.gone()
    }

    private fun showPlayer() {
        pfBackground.gone()
        pfPlayerView.visible()
        pfControlsView.visible()
    }

    private fun init(pulpFiction: PulpFiction) {
        pfControlsView.init(pulpFiction, this)
        pulpFiction
                .events
                .filter { it is PlayerInfo }
                .doOnNext { render((it as PlayerInfo).state) }
                .doOnNext { pfControlsView.render(ControlsViewState(pulpFiction.state)) }
                .subscribe()
                .addTo(disposables)
    }

    private fun deinit() {
        pfControlsView.deinit()
        disposables.clear()
    }

}