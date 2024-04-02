package com.bbasoglu.swipefun.matchmaker.profile.ui.adapter.model.mapper

import com.bbasoglu.swipefun.matchmaker.profile.domain.model.RickMortyLikesDomainModel
import com.bbasoglu.swipefun.matchmaker.profile.ui.adapter.model.LocationUiModel
import com.bbasoglu.swipefun.matchmaker.profile.ui.adapter.model.OriginUiModel
import com.bbasoglu.swipefun.matchmaker.profile.ui.adapter.model.ProfileUiData


fun RickMortyLikesDomainModel.toProfileUiData() = ProfileUiData(
    dataIndex = this.dataIndex,
    id = this.id.toString(),
    name = this.name,
    status = this.status,
    species = this.species,
    type = this.type,
    gender = this.gender,
    image = this.image,
    origin = OriginUiModel(this.origin?.name,this.origin?.url),
    location = LocationUiModel(this.location?.name,this.location?.url),
    episode = this.episode,
    url = this.url,
    created = this.created,
)