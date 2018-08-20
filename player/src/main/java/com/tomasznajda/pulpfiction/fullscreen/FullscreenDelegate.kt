package com.tomasznajda.pulpfiction.fullscreen

import com.tomasznajda.pulpfiction.exception.FullscreenDisabledException
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class FullscreenDelegate {
    private val _stateChanges = PublishSubject.create<FullscreenState>()
    val stateChanges: Observable<FullscreenState> = _stateChanges
    var state = FullscreenState.DISABLED
        private set(value) {
            field = value
            _stateChanges.onNext(value)
        }
    private var enterFullscreenCallback: (() -> Boolean)? = null
    private var exitFullscreenCallback: (() -> Boolean)? = null

    fun enable(enterFullscreenCallback: () -> Boolean, exitFullscreenCallback: () -> Boolean) {
        this.enterFullscreenCallback = enterFullscreenCallback
        this.exitFullscreenCallback = exitFullscreenCallback
        state = FullscreenState.OFF
    }

    fun disable() {
        enterFullscreenCallback = null
        exitFullscreenCallback = null
        state = FullscreenState.DISABLED
    }

    fun enter() {
        if (state == FullscreenState.DISABLED) throw FullscreenDisabledException()
        if (enterFullscreenCallback?.invoke() == true) state = FullscreenState.ON
    }

    fun exit() {
        if (state == FullscreenState.DISABLED) throw FullscreenDisabledException()
        if (exitFullscreenCallback?.invoke() == true) state = FullscreenState.OFF
    }
}