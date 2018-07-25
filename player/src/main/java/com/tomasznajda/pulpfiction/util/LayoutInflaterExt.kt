package com.tomasznajda.pulpfiction.util

import android.view.LayoutInflater
import android.view.ViewGroup

fun ViewGroup.inflate(resource: Int, attachToRoot: Boolean = false) =
        LayoutInflater.from(context).inflate(resource, this, attachToRoot)!!