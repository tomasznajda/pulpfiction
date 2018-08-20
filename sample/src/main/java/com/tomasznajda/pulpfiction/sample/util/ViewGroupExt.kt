package com.tomasznajda.pulpfiction.sample.util

import android.view.ViewGroup
import com.tomasznajda.pulpfiction.util.gone
import com.tomasznajda.pulpfiction.util.visible

fun ViewGroup.showAllChildren() = (0 until childCount).forEach { getChildAt(it).visible() }

fun ViewGroup.hideAllChildrenExcept(id: Int) =
        (0 until childCount).forEach { getChildAt(it).let { if (it.id != id) it.gone() } }
