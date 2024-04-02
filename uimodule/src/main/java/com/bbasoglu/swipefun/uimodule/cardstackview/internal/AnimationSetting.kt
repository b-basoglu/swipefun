package com.bbasoglu.swipefun.uimodule.cardstackview.internal

import android.view.animation.Interpolator
import com.bbasoglu.swipefun.uimodule.cardstackview.Direction

interface AnimationSetting {
    val direction: Direction?
    val duration: Int
    val interpolator: Interpolator?
}
