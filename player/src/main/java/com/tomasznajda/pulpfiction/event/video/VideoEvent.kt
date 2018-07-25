package com.tomasznajda.pulpfiction.event.video

sealed class VideoEvent

data class VideoSizeChangedEvent(val width: Int, val height: Int, val unappliedRotationDegrees: Int, val pixelWidthHeightRatio: Float) : VideoEvent()
class RenderedFirstFrameEvent : VideoEvent()

