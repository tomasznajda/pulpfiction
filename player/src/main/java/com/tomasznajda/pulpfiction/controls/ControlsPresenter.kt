package com.tomasznajda.pulpfiction.controls

import com.tomasznajda.pulpfiction._base.BasePresenter
import io.reactivex.rxkotlin.subscribeBy

class ControlsPresenter : BasePresenter<ControlsContract.View>() {

    override fun attached() {
        view
                ?.playClicks
                ?.subscribeBy(onNext = { view?.play() })
                ?.save()

        view
                ?.pauseClicks
                ?.subscribeBy(onNext = { view?.pause() })
                ?.save()
    }
}