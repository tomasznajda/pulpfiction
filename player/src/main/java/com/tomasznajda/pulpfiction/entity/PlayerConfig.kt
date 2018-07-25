package com.tomasznajda.pulpfiction.entity

const val DEFAULT_USER_AGENT = "PulpFiction"

data class PlayerConfig(val userAgentHeader: String = DEFAULT_USER_AGENT)