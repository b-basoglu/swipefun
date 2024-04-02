package com.bbasoglu.swipefun.matchmaker.feed.data

import androidx.paging.PagingData
import com.bbasoglu.swipefun.matchmaker.common.data.model.response.RickAndMortyCharacterResponseItem
import com.bbasoglu.swipefun.matchmaker.common.data.model.response.RickyMortyCharacterResponseModel
import com.bbasoglu.swipefun.network.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface RickMortyGetCharactersRepository {
    fun getCharacters() : Flow<PagingData<RickAndMortyCharacterResponseItem>>
    suspend fun getCharacters(page:Int): NetworkResponse<RickyMortyCharacterResponseModel>

}