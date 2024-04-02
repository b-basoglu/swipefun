package com.bbasoglu.swipefun.matchmaker.profile.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bbasoglu.swipefun.matchmaker.profile.ui.R
import com.bbasoglu.swipefun.matchmaker.profile.ui.adapter.model.ProfileUiData
import com.bbasoglu.swipefun.matchmaker.profile.ui.databinding.RowLikesBinding
import com.bbasoglu.swipefun.uimodule.adapter.base.BaseAdapterRow
import com.bbasoglu.swipefun.uimodule.adapter.base.BaseViewHolder
import com.bbasoglu.swipefun.uimodule.adapter.BaseAdapterParent

class ProfileUiRow :
    BaseAdapterRow<ProfileUiRow.ViewHolder, ProfileUiData>() {
    override fun getViewType() = R.layout.row_likes

    override fun getDataType() = ProfileUiData::class

    override fun onCreateViewHolder(
        parent: ViewGroup,
        adapter: BaseAdapterParent,
        adapterClick: ((Any) -> Unit)?
    ): ViewHolder {
        val binding = RowLikesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, adapterClick)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        data: ProfileUiData,
        position: Int
    ) {
        holder.bind(data, position)
    }

    inner class ViewHolder(
        binding: RowLikesBinding,
        val adapterClick: ((Any) -> Unit)?
    ) :
        BaseViewHolder<ProfileUiData, RowLikesBinding>(
            binding
        ) {
        override fun bindHolder(data: ProfileUiData, position: Int) {
            data.image?.let {
                binding.image.setUrlImage(
                    it,
                    cornerRadius = 12,
                    useShimmerEffect = false,
                    placeHolderResource = com.bbasoglu.swipefun.uimodule.R.drawable.bg_image_place_holder_12
                )
            }
            data.name?.let {
                binding.typeText.text = data.name
            }
            binding.root.setOnClickListener {
                adapterClick?.invoke(data)
            }
        }
    }
}