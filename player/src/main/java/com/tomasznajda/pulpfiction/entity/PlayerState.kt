package com.tomasznajda.pulpfiction.entity

enum class PlayerState {
    IDLE,
    INITIALIZED,
    BUFFERING,
    PLAYING,
    PAUSED,
    FINISHED,
    ERROR;

    val isIdle: Boolean
        get() = this == IDLE

    val isInitialized: Boolean
        get() = this == INITIALIZED

    val isBuffering: Boolean
        get() = this == BUFFERING

    val isPlaying: Boolean
        get() = this == PLAYING

    val isPaused: Boolean
        get() = this == PAUSED

    val isFinished: Boolean
        get() = this == FINISHED

    val isError: Boolean
        get() = this == ERROR

}