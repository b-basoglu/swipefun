package com.bbasoglu.swipefun.matchmaker.feed.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bbasoglu.swipefun.matchmaker.feed.ui.R
import com.bbasoglu.swipefun.matchmaker.feed.ui.adapter.model.FeedData
import com.bbasoglu.swipefun.matchmaker.feed.ui.databinding.RowImagesBinding
import com.bbasoglu.swipefun.uimodule.adapter.BaseAdapterParent
import com.bbasoglu.swipefun.uimodule.adapter.base.BaseAdapterRow
import com.bbasoglu.swipefun.uimodule.adapter.base.BaseViewHolder
import com.bumptech.glide.Glide


class FeedRow :
    BaseAdapterRow<FeedRow.ViewHolder, FeedData>() {
    override fun getViewType() = R.layout.row_images

    override fun getDataType() = FeedData::class

    override fun onCreateViewHolder(
        parent: ViewGroup,
        adapter: BaseAdapterParent,
        adapterClick: ((Any) -> Unit)?
    ): ViewHolder {
        val binding = RowImagesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, adapterClick)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        data: FeedData,
        position: Int
    ) {
        holder.bind(data, position)
    }

    inner class ViewHolder(
        binding: RowImagesBinding,
        private val adapterClick: ((Any) -> Unit)?
    ) :
        BaseViewHolder<FeedData, RowImagesBinding>(
            binding
        ) {
        @SuppressLint("SetTextI18n")
        override fun bindHolder(data: FeedData, position: Int) {
            binding.itemName.text = "${data.name}"
            binding.itemCity.text = data.location?.name
            Glide.with(binding.root.context)
                .load(data.image)
                .into(binding.itemImage)
            binding.root.setOnClickListener {
                adapterClick?.invoke(data)
            }
            binding.itemAvailable.text = data.status
        }
    }
}