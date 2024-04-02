package com.bbasoglu.swipefun.matchmaker.feed.domain.repository

import com.bbasoglu.swipefun.matchmaker.common.data.db.RickMortyDatabase
import com.bbasoglu.swipefun.matchmaker.common.data.model.entity.RickMortyEntity
import com.bbasoglu.swipefun.matchmaker.feed.data.RickMortyLikesInsertRepository
import javax.inject.Inject

class RickMortyLikesInsertRepositoryImpl@Inject constructor(private val database: RickMortyDatabase): RickMortyLikesInsertRepository {

    override fun insertRickMorty(rickMortyEntity: RickMortyEntity) {
        database.rickMortyDao().insertRickMorty(rickMortyEntity)
    }

    override fun getRickMortyWithId(id: Int?): RickMortyEntity? {
        return database.rickMortyDao().getRickMortyWithId(id)
    }

}