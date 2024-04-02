package com.bbasoglu.swipefun.matchmaker.feed.domain.model



data class RickAndMortyCharacterDomainModel(
    val id: Int?,
    val name: String?,
    val status: String?,
    val species : String?,
    val type : String?,
    val gender : String?,
    val image: String?,
    val origin: OriginDomainModel?,
    val location: LocationDomainModel?,
    val episode: List<String>?,
    val url: String?,
    val created: String?,
)