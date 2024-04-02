package com.bbasoglu.swipefun.matchmaker.feed.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bbasoglu.swipefun.matchmaker.common.data.model.response.RickAndMortyCharacterResponseItem
import com.bbasoglu.swipefun.matchmaker.common.data.model.response.RickyMortyCharacterResponseModel
import com.bbasoglu.swipefun.matchmaker.common.data.network.RickyMortyApiHelper
import com.bbasoglu.swipefun.matchmaker.feed.data.CharacterPagingSource
import com.bbasoglu.swipefun.matchmaker.feed.data.RickMortyGetCharactersRepository
import com.bbasoglu.swipefun.network.NetworkResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RickMortyGetCharactersRepositoryImpl @Inject constructor(
    private val characterPagingSource: CharacterPagingSource,
    private val rickyMortyApiHelper: RickyMortyApiHelper
): RickMortyGetCharactersRepository {
    override fun getCharacters(): Flow<PagingData<RickAndMortyCharacterResponseItem>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                characterPagingSource
            }
        ).flow
    }
    override suspend fun getCharacters(page:Int): NetworkResponse<RickyMortyCharacterResponseModel> = rickyMortyApiHelper.getCharacters(page)

}