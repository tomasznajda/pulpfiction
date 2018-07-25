package com.tomasznajda.pulpfiction._base

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

abstract class BaseView<ViewT> : FrameLayout {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    abstract val presenter: BasePresenter<ViewT>

    @Suppress("UNCHECKED_CAST")
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.attach(this as ViewT)
    }

    override fun onDetachedFromWindow() {
        presenter.detach()
        super.onDetachedFromWindow()
    }
}