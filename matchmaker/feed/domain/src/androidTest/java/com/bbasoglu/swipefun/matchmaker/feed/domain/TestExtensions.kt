package com.bbasoglu.swipefun.matchmaker.feed.domain

import com.bbasoglu.swipefun.matchmaker.common.data.model.entity.RickMortyEntity
import com.bbasoglu.swipefun.matchmaker.feed.domain.model.LocationDomainModel
import com.bbasoglu.swipefun.matchmaker.feed.domain.model.OriginDomainModel
import com.bbasoglu.swipefun.matchmaker.feed.domain.model.RickAndMortyCharacterDomainModel

fun RickMortyEntity.toRickAndMortyDomainModel() = RickAndMortyCharacterDomainModel(
    id = this.id,
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
