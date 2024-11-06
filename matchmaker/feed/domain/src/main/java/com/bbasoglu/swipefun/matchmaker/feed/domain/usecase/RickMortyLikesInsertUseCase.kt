package com.bbasoglu.swipefun.matchmaker.feed.domain.usecase

import com.bbasoglu.swipefun.di.IoDispatcher
import com.bbasoglu.swipefun.matchmaker.feed.data.RickMortyLikesInsertRepository
import com.bbasoglu.swipefun.matchmaker.feed.domain.model.RickAndMortyCharacterDomainModel
import com.bbasoglu.swipefun.matchmaker.feed.domain.model.mapper.toRickAndMortyEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import android.location.Location

class RickMortyLikesInsertUseCase @Inject constructor(
    private val rickMortyLikesInsertRepository: RickMortyLikesInsertRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun execute(param:Param) {
        try {
            withContext(dispatcher){
                rickMortyLikesInsertRepository.insertRickMorty(param.rickAndMortyCharacterDomainModel.toRickAndMortyEntity())
            }
        } catch (_: Exception) {

        }

    }


    data class Param(val rickAndMortyCharacterDomainModel: RickAndMortyCharacterDomainModel)
}