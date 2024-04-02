package com.bbasoglu.swipefun.uimodule.cardstackview.internal

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bbasoglu.swipefun.uimodule.cardstackview.CardStackLayoutManager
import com.bbasoglu.swipefun.uimodule.cardstackview.Direction

class CardStackSmoothScroller(
    private val type: ScrollType,
    private val manager: CardStackLayoutManager
) : RecyclerView.SmoothScroller() {
    enum class ScrollType {
        AutomaticSwipe,
        AutomaticRewind,
        ManualSwipe,
        ManualCancel
    }

    override fun onSeekTargetStep(
        dx: Int,
        dy: Int,
        state: RecyclerView.State,
        action: Action
    ) {
        if (type == ScrollType.AutomaticRewind) {
            val setting = manager.cardStackSetting.rewindAnimationSetting
            action.update(
                -getDx(setting),
                -getDy(setting),
                setting.duration,
                setting.interpolator
            )
        }
    }

    override fun onTargetFound(
        targetView: View,
        state: RecyclerView.State,
        action: Action
    ) {
        val x = targetView.translationX.toInt()
        val y = targetView.translationY.toInt()
        val setting: AnimationSetting
        when (type) {
            ScrollType.AutomaticSwipe -> {
                setting = manager.cardStackSetting.swipeAnimationSetting
                action.update(
                    -getDx(setting),
                    -getDy(setting),
                    setting.duration,
                    setting.interpolator
                )
            }

            ScrollType.AutomaticRewind -> {
                setting = manager.cardStackSetting.rewindAnimationSetting
                action.update(
                    x,
                    y,
                    setting.duration,
                    setting.interpolator
                )
            }

            ScrollType.ManualSwipe -> {
                val dx = -x * 10
                val dy = -y * 10
                setting = manager.cardStackSetting.swipeAnimationSetting
                action.update(
                    dx,
                    dy,
                    setting.duration,
                    setting.interpolator
                )
            }

            ScrollType.ManualCancel -> {
                setting = manager.cardStackSetting.rewindAnimationSetting
                action.update(
                    x,
                    y,
                    setting.duration,
                    setting.interpolator
                )
            }
        }
    }

    override fun onStart() {
        val listener = manager.cardStackListener
        val state = manager.cardStackState
        when (type) {
            ScrollType.AutomaticSwipe -> {
                state.next(CardStackState.Status.AutomaticSwipeAnimating)
                listener.onCardDisappeared(manager.topView, manager.topPosition)
            }

            ScrollType.AutomaticRewind -> state.next(CardStackState.Status.RewindAnimating)
            ScrollType.ManualSwipe -> {
                state.next(CardStackState.Status.ManualSwipeAnimating)
                listener.onCardDisappeared(manager.topView, manager.topPosition)
            }

            ScrollType.ManualCancel -> state.next(CardStackState.Status.RewindAnimating)
        }
    }

    override fun onStop() {
        val listener = manager.cardStackListener
        when (type) {
            ScrollType.AutomaticSwipe -> {}
            ScrollType.AutomaticRewind -> {
                listener.onCardRewound()
                listener.onCardAppeared(manager.topView, manager.topPosition)
            }

            ScrollType.ManualSwipe -> {}
            ScrollType.ManualCancel -> listener.onCardCanceled()
        }
    }

    private fun getDx(setting: AnimationSetting): Int {
        val state = manager.cardStackState
        var dx = 0
        when (setting.direction) {
            Direction.Left -> dx = -state.width * 2
            Direction.Right -> dx = state.width * 2
            Direction.Top, Direction.Bottom -> dx = 0
            null -> {

            }
        }
        return dx
    }

    private fun getDy(setting: AnimationSetting): Int {
        val state = manager.cardStackState
        var dy = 0
        when (setting.direction) {
            Direction.Left, Direction.Right -> dy = state.height / 4
            Direction.Top -> dy = -state.height * 2
            Direction.Bottom -> dy = state.height * 2
            null -> {

            }
        }
        return dy
    }
}
