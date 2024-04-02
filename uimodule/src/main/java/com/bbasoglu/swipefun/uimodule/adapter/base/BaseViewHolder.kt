package com.bbasoglu.swipefun.uimodule.adapter.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder <D : BaseAdapterData, T : ViewBinding>(
    protected val binding: T,
) : RecyclerView.ViewHolder(binding.root) {

    protected abstract fun bindHolder(data: D, position: Int)
    open fun onViewRecycled() {

    }

    open fun onViewAttachedToWindow() {

    }

    open fun onViewDetachedFromWindow() {

    }

    fun bind(data: D, position: Int) {
        bindHolder(data, position)
    }
}

