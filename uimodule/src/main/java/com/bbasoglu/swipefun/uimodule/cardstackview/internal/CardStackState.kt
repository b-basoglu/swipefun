package com.bbasoglu.swipefun.uimodule.cardstackview.internal

import androidx.recyclerview.widget.RecyclerView
import com.bbasoglu.swipefun.uimodule.cardstackview.Direction
import kotlin.math.abs
import kotlin.math.min

class CardStackState {
    var status = Status.Idle
    var width = 0
    var height = 0
    var dx = 0
    var dy = 0
    var topPosition = 0
    var targetPosition = RecyclerView.NO_POSITION
    var proportion = 0.0f

    enum class Status {
        Idle,
        Dragging,
        RewindAnimating,
        AutomaticSwipeAnimating,
        AutomaticSwipeAnimated,
        ManualSwipeAnimating,
        ManualSwipeAnimated;

        val isBusy: Boolean
            get() = this != Idle
        val isDragging: Boolean
            get() = this == Dragging
        val isSwipeAnimating: Boolean
            get() = this == ManualSwipeAnimating || this == AutomaticSwipeAnimating

        fun toAnimatedStatus(): Status {
            return when (this) {
                ManualSwipeAnimating -> ManualSwipeAnimated
                AutomaticSwipeAnimating -> AutomaticSwipeAnimated
                else -> Idle
            }
        }
    }

    fun next(state: Status) {
        status = state
    }

    val direction: Direction
        get() = if (abs(dy.toDouble()) < abs(dx.toDouble())) {
            if (dx < 0.0f) {
                Direction.Left
            } else {
                Direction.Right
            }
        } else {
            if (dy < 0.0f) {
                Direction.Top
            } else {
                Direction.Bottom
            }
        }
    val ratio: Float
        get() {
            val absDx = abs(dx.toDouble()).toInt()
            val absDy = abs(dy.toDouble()).toInt()
            val ratio: Float
            ratio = if (absDx < absDy) {
                absDy / (height / 2.0f)
            } else {
                absDx / (width / 2.0f)
            }
            return min(ratio.toDouble(), 1.0).toFloat()
        }
    val isSwipeCompleted: Boolean
        get() {
            if (status.isSwipeAnimating) {
                if (topPosition < targetPosition) {
                    if (width < abs(dx.toDouble()) || height < abs(dy.toDouble())) {
                        return true
                    }
                }
            }
            return false
        }

    fun canScrollToPosition(position: Int, itemCount: Int): Boolean {
        if (position == topPosition) {
            return false
        }
        if (position < 0) {
            return false
        }
        if (itemCount < position) {
            return false
        }
        return if (status.isBusy) {
            false
        } else true
    }
}
