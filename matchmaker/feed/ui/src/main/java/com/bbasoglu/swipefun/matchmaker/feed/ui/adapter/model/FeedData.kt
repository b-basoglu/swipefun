package com.bbasoglu.swipefun.matchmaker.feed.ui.adapter.model

import com.bbasoglu.swipefun.matchmaker.feed.domain.model.LocationDomainModel
import com.bbasoglu.swipefun.matchmaker.feed.domain.model.OriginDomainModel
import com.bbasoglu.swipefun.uimodule.adapter.base.BaseAdapterData
import kotlinx.parcelize.Parcelize


@Parcelize
data class FeedData(
    override val id: String?,
    val name: String?,
    val status: String?,
    val species : String?,
    val type : String?,
    val gender : String?,
    val image: String?,
    val origin: OriginUiData?,
    val location: LocationUiData?,
    val episode: List<String>?,
    val url: String?,
    val created: String?,
):BaseAdapterData