package com.bbasoglu.swipefun.matchmaker.profile.domain.model


data class RickMortyLikesDomainModel(
    var dataIndex: Int? = null,
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