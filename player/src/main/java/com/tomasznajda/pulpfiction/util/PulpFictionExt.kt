package com.tomasznajda.pulpfiction.util

import com.tomasznajda.pulpfiction.PulpFiction
import com.tomasznajda.pulpfiction.entity.Clip

fun PulpFiction.load(url: String, autoplay: Boolean = true) = load(Clip(url), autoplay)