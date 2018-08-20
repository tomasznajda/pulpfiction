package com.tomasznajda.pulpfiction.sample.util

import android.os.Build
import android.view.View

enum class UiMode(val flag: Int) {
    FULLSCREEN_DISABLED(View.SYSTEM_UI_FLAG_VISIBLE),
    FULLSCREEN_ENABLED(View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY else 0)
}