package com.bbasoglu.swipefun.uimodule.adapter.expandable

import android.view.ViewGroup
import com.bbasoglu.swipefun.uimodule.adapter.BaseAdapterParent
import kotlin.reflect.KClass

abstract class BaseAdapterExpandableRow<VH : BaseExpandableViewHolder<*, *>, D : BaseExpandableListData> {
    abstract fun getViewType(): Int
    abstract fun getDataType(): KClass<out BaseExpandableListData>
    abstract fun onCreateViewHolder(
        parent: ViewGroup,
        adapter: BaseAdapterParent,
        adapterClick: ((Any) -> Unit)? = null,
        onItemExpanded: OnItemExpanded
    ): VH

    abstract fun onBindViewHolder(holder: VH, data: D, position: Int)

    interface OnItemExpanded {
        fun onClick(item: BaseExpandableListData, position: Int)
    }
}