package com.bbasoglu.swipefun.uimodule.adapter.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class StaggeredGridItemDecoration(
    private val spacingMiddleHalf: Int,
    private val spacingLeft: Int,
    private val spacingRight: Int,
    private val spacingTop: Int,
    private val spacingBottom: Int
    ) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.layoutManager is StaggeredGridLayoutManager) {
            val layoutParams =
                view.layoutParams as StaggeredGridLayoutManager.LayoutParams

            val spanIndex = layoutParams.spanIndex
            val spanCount =
                (parent.layoutManager as StaggeredGridLayoutManager).spanCount

            if (layoutParams.isFullSpan) {
                // This is a full-span item, apply fullSpanSpacing
            } else {
                // This is a regular item, apply regular spacing
                // Calculate top and bottom spacing
                outRect.top = spacingTop
                outRect.bottom = spacingBottom

                // Calculate left and right spacing
                if (spanIndex == 0) {
                    // Item is in the leftmost column
                    outRect.left = spacingLeft
                    outRect.right = spacingMiddleHalf
                } else if (spanIndex == spanCount - 1) {
                    // Item is in the rightmost column
                    outRect.left = spacingMiddleHalf
                    outRect.right = spacingRight
                } else {
                    // Item is in the middle columns
                    outRect.left = spacingMiddleHalf
                    outRect.right = spacingMiddleHalf
                }
            }
        }
    }
}
