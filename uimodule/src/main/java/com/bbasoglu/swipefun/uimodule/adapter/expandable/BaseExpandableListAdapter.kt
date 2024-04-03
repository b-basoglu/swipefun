package com.bbasoglu.swipefun.uimodule.adapter.expandable

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bbasoglu.swipefun.uimodule.adapter.BaseAdapterParent
import com.bbasoglu.swipefun.uimodule.adapter.baseAdapterExpandableDC

abstract class BaseExpandableListAdapter (
    private val adapterRowTypes: List<BaseAdapterExpandableRow<out BaseExpandableViewHolder<*, *>, out BaseExpandableListData>>,
    private val adapterClick: ((Any) -> Unit)? = null,
) : ListAdapter<BaseExpandableListData, BaseExpandableViewHolder<*, *>>(baseAdapterExpandableDC),
    BaseAdapterParent {

    private var lastExpandedItemId: String? = null

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        val rowType = adapterRowTypes.find { it.getDataType().isInstance(item) }?.getViewType()
        rowType?.let {
            return it
        } ?: throw RuntimeException()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseExpandableViewHolder<*, *> {
        return findRowType(viewType).onCreateViewHolder(parent, this, adapterClick,onItemExpanded)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: BaseExpandableViewHolder<*, *>, position: Int) {
        val rowType =
            findRowTypeByDataType(getItem(position)) as BaseAdapterExpandableRow<BaseExpandableViewHolder<*, *>, BaseExpandableListData>
        rowType.onBindViewHolder(holder, getItem(position), position)
    }

    private fun findRowType(viewType: Int): BaseAdapterExpandableRow<*, *> {
        val rowType = adapterRowTypes.find { rowType -> rowType.getViewType() == viewType }
        if (rowType != null)
            return rowType
        else
            throw RuntimeException()
    }

    private val onItemExpanded = object : BaseAdapterExpandableRow.OnItemExpanded {
        @SuppressLint("NotifyDataSetChanged")
        override fun onClick(item: BaseExpandableListData, position: Int) {
            if (!TextUtils.isEmpty(lastExpandedItemId) && !item.id.equals(lastExpandedItemId)) {
                val oldItem = findItemWithId(lastExpandedItemId)
                oldItem?.let {
                    if (it.second.isExpanded) {
                        it.second.isExpanded = !it.second.isExpanded
                        try {
                            notifyItemChanged(it.first, it.second)
                        } catch (e: Exception) {
                            notifyDataSetChanged()
                            e.printStackTrace()
                        }
                    }
                }
            }
            lastExpandedItemId = if (!item.isExpanded) {
                item.id
            } else {
                null
            }
            item.isExpanded = !item.isExpanded
            notifyItemChanged(position, item)
        }
    }

    private fun findRowTypeByDataType(dataType: BaseExpandableListData): BaseAdapterExpandableRow<*, *> {
        val rowType = adapterRowTypes.find {
            it.getDataType().isInstance(dataType)
        }
        if (rowType != null)
            return rowType
        else
            throw RuntimeException()
    }

    private fun findItemWithId(id: String?): Pair<Int, BaseExpandableListData>? {
        for ((index, value) in this.currentList.withIndex()) {
            if (value.id.equals(id)) {
                return Pair(index, value)
            }
        }
        return null
    }
}

