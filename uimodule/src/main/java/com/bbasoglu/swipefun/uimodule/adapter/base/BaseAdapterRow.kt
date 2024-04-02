package com.bbasoglu.swipefun.uimodule.adapter.base

import android.view.ViewGroup
import com.bbasoglu.swipefun.uimodule.adapter.BaseAdapterParent
import kotlin.reflect.KClass

abstract class BaseAdapterRow<VH : BaseViewHolder<*, *>, D : BaseAdapterData> {
    var isFullSpan = false // Only use for staggared
    abstract fun getViewType(): Int
    abstract fun getDataType(): KClass<out BaseAdapterData>
    abstract fun onCreateViewHolder(
        parent: ViewGroup,
        adapter: BaseAdapterParent,
        adapterClick: ((Any) -> Unit)? = null
    ): VH

    abstract fun onBindViewHolder(holder: VH, data: D, position: Int)
}