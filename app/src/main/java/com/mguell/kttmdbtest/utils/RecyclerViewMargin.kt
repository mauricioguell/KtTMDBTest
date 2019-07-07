package com.mguell.kttmdbtest.utils

import android.graphics.Rect
import android.view.View

import androidx.annotation.IntRange
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewMargin(
    @param:IntRange(from = 0) private val margin: Int,
    @param:IntRange(from = 0) private val columns: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val position = parent.getChildLayoutPosition(view)
        outRect.right = margin
        outRect.bottom = margin
        if (position < columns) {
            outRect.top = margin
        }
        if (position % columns == 0) {
            outRect.left = margin
        }
    }
}
