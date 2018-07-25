package com.tomasznajda.pulpfiction.__util

import java.io.IOException
import java.util.*

data class ComparableException(val salt: Int = Random().nextInt()) : Exception()

data class ComparableIOException(val salt: Int = Random().nextInt()) : IOException()