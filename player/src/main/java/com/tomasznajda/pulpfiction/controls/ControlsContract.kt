package com.tomasznajda.pulpfiction.controls

import com.tomasznajda.pulpfiction.entity.ControlsViewState
import io.reactivex.Observable


interface ControlsContract {

    interface View {
        val playClicks: Observable<Unit>
        val pauseClicks: Observable<Unit>
        fun render(state: ControlsViewState)
        fun play()
        fun pause()
    }
}