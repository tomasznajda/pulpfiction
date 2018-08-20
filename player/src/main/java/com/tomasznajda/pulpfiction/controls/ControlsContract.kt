package com.tomasznajda.pulpfiction.controls

import com.tomasznajda.pulpfiction.entity.ControlsViewState
import io.reactivex.Observable


interface ControlsContract {

    interface View {
        val playClicks: Observable<Unit>
        val pauseClicks: Observable<Unit>
        val enterFullscreenClicks: Observable<Unit>
        val exitFullscreenClicks: Observable<Unit>
        fun render(state: ControlsViewState)
        fun play()
        fun pause()
        fun enterFullscreen()
        fun exitFullscreen()
    }
}