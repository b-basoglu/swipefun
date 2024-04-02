package com.bbasoglu.swipefun.matchmaker.profile.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bbasoglu.swipefun.matchmaker.common.data.db.RickMortyDatabase
import com.bbasoglu.swipefun.matchmaker.common.data.model.entity.RickMortyEntity
import com.bbasoglu.swipefun.matchmaker.profile.data.repository.RickMortyLikesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RickMortyLikesRepositoryImpl@Inject constructor(private val database: RickMortyDatabase):
    RickMortyLikesRepository {

    override fun getRickMortyPaging(): Flow<PagingData<RickMortyEntity>> {
        val pagingSourceFactory = { database.rickMortyDao().getRickMortyPaging() }
        return Pager(
            config = PagingConfig(7, enablePlaceholders = false),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun getRickMorty(): List<RickMortyEntity> {
        return database.rickMortyDao().getRickMorty()
    }

    override fun getRickMortyFlow(): Flow<List<RickMortyEntity>> {
        return database.rickMortyDao().getRickMortyFlow()
    }

    override fun getRickMortyWithId(id: Int?): RickMortyEntity? {
        return database.rickMortyDao().getRickMortyWithId(id)
    }

    override fun getRickMortyRowCount() = database.rickMortyDao().getRowCount()

    override fun deleteRickMorty(rickMortyEntity: RickMortyEntity) {
        database.rickMortyDao().deleteRickMorty(rickMortyEntity)
    }

    override fun updateRickMorty(rickMortyEntity: RickMortyEntity) {
        database.rickMortyDao().updateRickMorty(rickMortyEntity)
    }

    override fun insertRickMorty(rickMortyEntity: RickMortyEntity) {
        database.rickMortyDao().insertRickMorty(rickMortyEntity)
    }

}
