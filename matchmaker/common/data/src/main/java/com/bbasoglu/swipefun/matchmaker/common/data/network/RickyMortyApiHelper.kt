package com.bbasoglu.swipefun.matchmaker.common.data.network

import com.bbasoglu.swipefun.matchmaker.common.data.model.response.RickyMortyCharacterResponseModel
import com.bbasoglu.swipefun.network.NetworkResponse


interface RickyMortyApiHelper {
    suspend fun getCharacters(
        page: Int
    ): NetworkResponse<RickyMortyCharacterResponseModel>
}