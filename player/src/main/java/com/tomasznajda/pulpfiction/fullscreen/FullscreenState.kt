package com.tomasznajda.pulpfiction.fullscreen

enum class FullscreenState {
    ON,
    OFF,
    DISABLED;

    val isOn: Boolean
        get() = this == ON

    val isOff: Boolean
        get() = this == OFF

    val isDisabled: Boolean
        get() = this == DISABLED
}