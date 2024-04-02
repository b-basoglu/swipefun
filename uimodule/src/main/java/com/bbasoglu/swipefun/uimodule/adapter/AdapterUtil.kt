package com.bbasoglu.swipefun.uimodule.adapter

import androidx.recyclerview.widget.DiffUtil
import com.bbasoglu.swipefun.uimodule.adapter.base.BaseAdapterData
import com.bbasoglu.swipefun.uimodule.adapter.expandable.BaseExpandableListData

val baseAdapterDC = object : DiffUtil.ItemCallback<BaseAdapterData>() {
    override fun areItemsTheSame(oldItem: BaseAdapterData, newItem: BaseAdapterData) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: BaseAdapterData, newItem: BaseAdapterData) =
        oldItem == newItem
}


val baseAdapterExpandableDC = object : DiffUtil.ItemCallback<BaseExpandableListData>() {
    override fun areItemsTheSame(oldItem: BaseExpandableListData, newItem: BaseExpandableListData) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: BaseExpandableListData, newItem: BaseExpandableListData) =
        oldItem == newItem
}