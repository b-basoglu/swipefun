package com.bbasoglu.swipefun.matchmaker.feed.data

import com.bbasoglu.swipefun.matchmaker.common.data.model.entity.RickMortyEntity

interface RickMortyLikesInsertRepository {

    fun insertRickMorty(rickMortyEntity: RickMortyEntity)

    fun getRickMortyWithId(id: Int?): RickMortyEntity?
}