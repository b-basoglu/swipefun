package com.bbasoglu.swipefun.matchmaker.feed.composeui.model


data class FeedData(
    val id: String?,
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
)