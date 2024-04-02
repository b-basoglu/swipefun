package com.bbasoglu.swipefun.matchmaker.feed.composeui.model.mapper

import com.bbasoglu.swipefun.matchmaker.feed.domain.model.LocationDomainModel
import com.bbasoglu.swipefun.matchmaker.feed.domain.model.OriginDomainModel
import com.bbasoglu.swipefun.matchmaker.feed.domain.model.RickAndMortyCharacterDomainModel
import com.bbasoglu.swipefun.matchmaker.feed.composeui.model.FeedData
import com.bbasoglu.swipefun.matchmaker.feed.composeui.model.LocationUiData
import com.bbasoglu.swipefun.matchmaker.feed.composeui.model.OriginUiData


fun RickAndMortyCharacterDomainModel.toFeedData() = FeedData(
    id = this.id.toString(),
    name = this.name,
    status = this.status,
    species = this.species,
    type = this.type,
    gender = this.gender,
    image = this.image,
    origin = OriginUiData(this.origin?.name,this.origin?.url),
    location = LocationUiData(this.location?.name,this.location?.url),
    episode = this.episode,
    url = this.url,
    created = this.created,
)

fun FeedData.toRickAndMortyCharacterDomainModel() =
    RickAndMortyCharacterDomainModel(
        id = this.id?.toInt(),
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        image = this.image,
        origin = OriginDomainModel(this.origin?.name,this.origin?.url),
        location = LocationDomainModel(this.location?.name,this.location?.url),
        episode = this.episode,
        url = this.url,
        created = this.created,
    )