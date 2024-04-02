package com.bbasoglu.swipefun.uimodule.adapter.expandable

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseExpandableViewHolder  <D : BaseExpandableListData, T : ViewBinding>(
    protected val binding: T,
) : RecyclerView.ViewHolder(binding.root) {

    protected abstract fun bindHolder(data: D, position: Int)

    fun bind(data: D, position: Int) {
        bindHolder(data, position)
    }
}