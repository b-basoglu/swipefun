package com.bbasoglu.swipefun.matchmaker.feed.domain.model.mapper

import android.net.Uri
import com.bbasoglu.swipefun.matchmaker.common.data.model.entity.LocationEntity
import com.bbasoglu.swipefun.matchmaker.common.data.model.entity.OriginEntity
import com.bbasoglu.swipefun.matchmaker.common.data.model.entity.RickMortyEntity
import com.bbasoglu.swipefun.matchmaker.common.data.model.response.Info
import com.bbasoglu.swipefun.matchmaker.common.data.model.response.RickAndMortyCharacterResponseItem
import com.bbasoglu.swipefun.matchmaker.feed.domain.model.LocationDomainModel
import com.bbasoglu.swipefun.matchmaker.feed.domain.model.OriginDomainModel
import com.bbasoglu.swipefun.matchmaker.feed.domain.model.RickAndMortyCharacterDomainModel

fun Info.toPageIndexMapper(page:Int):Int{
    val uri = Uri.parse(this.next)
    val nextPageQuery = uri.getQueryParameter("page")
    return nextPageQuery?.toInt() ?: let {
        page+1
    }
}

fun RickAndMortyCharacterResponseItem.toRickAndMortyCharacterDomainModel() = RickAndMortyCharacterDomainModel(
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
fun RickAndMortyCharacterDomainModel.toRickAndMortyEntity() = RickMortyEntity(
    id = this.id,
    name = this.name,
    status = this.status,
    species = this.species,
    type = this.type,
    gender = this.gender,
    image = this.image,
    origin = OriginEntity(this.origin?.name,this.origin?.url),
    location = LocationEntity(this.location?.name,this.location?.url),
    episode = this.episode,
    url = this.url,
    created = this.created,
)