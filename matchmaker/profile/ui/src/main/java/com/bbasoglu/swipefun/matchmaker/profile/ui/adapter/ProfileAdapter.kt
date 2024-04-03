package com.bbasoglu.swipefun.matchmaker.profile.ui.adapter

import com.bbasoglu.swipefun.uimodule.adapter.paginated.BasePagedAdapter

class ProfileAdapter(
    itemSelected: ((Any) -> Unit)? = null,
) : BasePagedAdapter(
    adapterRowTypes = listOf(
        ProfileUiRow(),
    ),
    adapterClick = itemSelected
)