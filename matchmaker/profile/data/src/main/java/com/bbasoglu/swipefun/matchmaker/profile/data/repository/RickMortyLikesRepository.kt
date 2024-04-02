package com.bbasoglu.swipefun.matchmaker.profile.data.repository

import androidx.paging.PagingData
import com.bbasoglu.swipefun.matchmaker.common.data.model.entity.RickMortyEntity
import kotlinx.coroutines.flow.Flow

interface RickMortyLikesRepository {

    fun getRickMortyPaging(): Flow<PagingData<RickMortyEntity>>

    fun getRickMorty(): List<RickMortyEntity>

    fun getRickMortyFlow() : Flow<List<RickMortyEntity>>

    fun getRickMortyWithId(id: Int?): RickMortyEntity?

    fun getRickMortyRowCount(): Flow<Int>

    fun deleteRickMorty(rickMortyEntity: RickMortyEntity)

    fun updateRickMorty(rickMortyEntity: RickMortyEntity)

    fun insertRickMorty(rickMortyEntity: RickMortyEntity)
}