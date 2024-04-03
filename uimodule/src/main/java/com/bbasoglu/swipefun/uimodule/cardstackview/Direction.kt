package com.bbasoglu.swipefun.uimodule.cardstackview


enum class Direction {
    Left,
    Right,
    Top,
    Bottom;

    companion object {
        val HORIZONTAL: List<Direction> = listOf(Left, Right)
    }
}
