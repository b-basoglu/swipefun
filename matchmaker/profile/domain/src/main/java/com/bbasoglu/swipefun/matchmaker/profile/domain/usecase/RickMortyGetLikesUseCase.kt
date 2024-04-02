package com.bbasoglu.swipefun.matchmaker.profile.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.bbasoglu.swipefun.matchmaker.profile.data.repository.RickMortyLikesRepository
import com.bbasoglu.swipefun.matchmaker.profile.domain.model.RickMortyLikesDomainModel
import com.bbasoglu.swipefun.matchmaker.profile.domain.model.mapper.toRickMortyLikesDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RickMortyGetLikesUseCase  @Inject constructor(
    private val rickMortyRepository: RickMortyLikesRepository,
) {
    fun execute(): Flow<PagingData<RickMortyLikesDomainModel>> =
        rickMortyRepository.getRickMortyPaging().map {pagingData ->
            pagingData.map { character ->
                character.toRickMortyLikesDomainModel()
            }
        }
}