package com.tomasznajda.pulpfiction._base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import java.lang.ref.WeakReference

abstract class BasePresenter<ViewT> {

    private val disposables = CompositeDisposable()
    private var _view: WeakReference<ViewT>? = null
    protected val view: ViewT?
        get() = _view?.get()

    protected open fun attached() {}

    protected open fun detached() {}

    fun attach(view: ViewT) {
        _view = WeakReference(view)
        attached()
    }

    fun detach() {
        _view = null
        disposables.clear()
        detached()
    }

    fun Disposable.save() = addTo(disposables)
}