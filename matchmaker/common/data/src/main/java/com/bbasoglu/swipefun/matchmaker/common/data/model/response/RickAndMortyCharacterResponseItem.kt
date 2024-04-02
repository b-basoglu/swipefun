package com.bbasoglu.swipefun.matchmaker.common.data.model.response


data class RickAndMortyCharacterResponseItem(
    val id: Int?,
    val name: String?,
    val status: String?,
    val species : String?,
    val type : String?,
    val gender : String?,
    val image: String?,
    val origin: Origin?,
    val location: Location?,
    val episode: List<String>?,
    val url: String?,
    val created: String?,
)
