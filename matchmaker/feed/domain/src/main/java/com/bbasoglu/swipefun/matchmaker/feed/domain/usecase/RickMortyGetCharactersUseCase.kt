package com.bbasoglu.swipefun.matchmaker.feed.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.bbasoglu.swipefun.matchmaker.feed.data.RickMortyGetCharactersRepository
import com.bbasoglu.swipefun.matchmaker.feed.domain.model.RickAndMortyCharacterDomainModel
import com.bbasoglu.swipefun.matchmaker.feed.domain.model.mapper.toRickAndMortyCharacterDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RickMortyGetCharactersUseCase  @Inject constructor(
    private val rickMortyRepository: RickMortyGetCharactersRepository,
) {
    fun execute(): Flow<PagingData<RickAndMortyCharacterDomainModel>> =
        rickMortyRepository.getCharacters().map {pagingData ->
            pagingData.map { character ->
                character.toRickAndMortyCharacterDomainModel()
            }
        }
}