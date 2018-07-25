package com.tomasznajda.pulpfiction

import com.tomasznajda.pulpfiction.entity.Clip
import com.tomasznajda.pulpfiction.event.player.PlayerEvent
import io.reactivex.Observable

interface Player {

    val events: Observable<PlayerEvent>

    var videoView: PfVideoView?

    fun load(clip: Clip, autoplay: Boolean)

    fun play()

    fun pause()

    fun stop()

    fun release()
}