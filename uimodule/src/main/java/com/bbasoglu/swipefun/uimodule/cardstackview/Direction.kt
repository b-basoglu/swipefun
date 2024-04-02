package com.bbasoglu.swipefun.uimodule.cardstackview

import java.util.Arrays

enum class Direction {
    Left,
    Right,
    Top,
    Bottom;

    companion object {
        val HORIZONTAL = Arrays.asList(Left, Right)
    }
}
