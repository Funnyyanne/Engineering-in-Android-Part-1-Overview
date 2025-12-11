package com.example.androiddemo.xml

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Grid Spacing Item Decoration
 * 网格间距装饰器 - 为 StaggeredGridLayoutManager 添加均匀间距
 */
class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacingDp: Int,
    context: android.content.Context
) : RecyclerView.ItemDecoration() {

    private val spacingPx = (spacingDp * context.resources.displayMetrics.density).toInt()

    override fun getItemOffsets(
        outRect: android.graphics.Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        // 水平间距：左右各一半
        outRect.left = spacingPx / 2
        outRect.right = spacingPx / 2

        // 垂直间距：底部
        outRect.bottom = spacingPx
    }
}