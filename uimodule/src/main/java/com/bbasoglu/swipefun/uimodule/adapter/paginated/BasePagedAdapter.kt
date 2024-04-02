package com.bbasoglu.swipefun.uimodule.adapter.paginated

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.bbasoglu.swipefun.uimodule.adapter.BaseAdapterParent
import com.bbasoglu.swipefun.uimodule.adapter.base.BaseAdapterData
import com.bbasoglu.swipefun.uimodule.adapter.base.BaseAdapterRow
import com.bbasoglu.swipefun.uimodule.adapter.base.BaseViewHolder
import com.bbasoglu.swipefun.uimodule.adapter.baseAdapterDC

abstract class BasePagedAdapter (
    private val adapterRowTypes: List<BaseAdapterRow<out BaseViewHolder<*, *>, out BaseAdapterData>>,
    private val adapterClick: ((Any) -> Unit)? = null,
) : PagingDataAdapter<BaseAdapterData, BaseViewHolder<*, *>>(baseAdapterDC), BaseAdapterParent {
    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        val rowType = adapterRowTypes.find { it.getDataType().isInstance(item) }?.getViewType()
        rowType?.let {
            return it
        } ?: throw RuntimeException()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> {
        return findRowType(viewType).onCreateViewHolder(parent, this, adapterClick)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) {
        getItem(position)?.let {
            val rowType =
                findRowTypeByDataType(it) as BaseAdapterRow<BaseViewHolder<*, *>, BaseAdapterData>
            rowType.onBindViewHolder(holder, it, position)
        }
    }

    private fun findRowType(viewType: Int): BaseAdapterRow<*, *> {
        val rowType = adapterRowTypes.find { rowType -> rowType.getViewType() == viewType }
        if (rowType != null)
            return rowType
        else
            throw RuntimeException()
    }

    private fun findRowTypeByDataType(dataType: BaseAdapterData): BaseAdapterRow<*, *> {
        val rowType = adapterRowTypes.find {
            it.getDataType().isInstance(dataType)
        }
        if (rowType != null)
            return rowType
        else
            throw RuntimeException()
    }

    override fun onViewRecycled(holder: BaseViewHolder<*, *>) {
        holder.onViewRecycled()
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<*, *>) {
        super.onViewAttachedToWindow(holder)
        holder.onViewAttachedToWindow()
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<*, *>) {
        super.onViewDetachedFromWindow(holder)
        holder.onViewDetachedFromWindow()
    }
}