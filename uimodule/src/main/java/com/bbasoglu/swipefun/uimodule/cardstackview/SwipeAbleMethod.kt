package com.bbasoglu.swipefun.uimodule.cardstackview

enum class SwipeAbleMethod {
    AutomaticAndManual,
    Automatic,
    Manual,
    None;

    fun canSwipe(): Boolean {
        return canSwipeAutomatically() || canSwipeManually()
    }

    fun canSwipeAutomatically(): Boolean {
        return this == AutomaticAndManual || this == Automatic
    }

    fun canSwipeManually(): Boolean {
        return this == AutomaticAndManual || this == Manual
    }
}
