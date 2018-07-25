package com.tomasznajda.pulpfiction.event.player

import com.tomasznajda.pulpfiction.entity.PlayerState

sealed class PlayerEvent

data class PlayerInfo(val state: PlayerState) : PlayerEvent()
data class PlayerError(val error: Throwable) : PlayerEvent()