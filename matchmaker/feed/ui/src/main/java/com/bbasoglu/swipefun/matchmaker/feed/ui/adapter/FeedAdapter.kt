package com.bbasoglu.swipefun.matchmaker.feed.ui.adapter

import com.bbasoglu.swipefun.uimodule.adapter.paginated.BasePagedAdapter


class FeedAdapter(
    itemSelected: ((Any) -> Unit)? = null,
) : BasePagedAdapter(
    adapterRowTypes = listOf(
        FeedRow()
    ),
    adapterClick = itemSelected
)