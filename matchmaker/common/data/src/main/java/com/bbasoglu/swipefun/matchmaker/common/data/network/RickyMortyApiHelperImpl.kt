package com.bbasoglu.swipefun.matchmaker.common.data.network

import com.bbasoglu.swipefun.matchmaker.common.data.di.RickMortyApiServiceInstance
import com.bbasoglu.swipefun.matchmaker.common.data.model.response.RickyMortyCharacterResponseModel
import com.bbasoglu.swipefun.network.NetworkResponse
import javax.inject.Inject

class RickyMortyApiHelperImpl @Inject constructor(
    @RickMortyApiServiceInstance private val rickyMortyApiService: RickyMortyApiService,
) : RickyMortyApiHelper {

    override suspend fun getCharacters(page: Int): NetworkResponse<RickyMortyCharacterResponseModel> {
        val response = rickyMortyApiService.getCharacters(page)
        return if (response.isSuccessful) {
            NetworkResponse.Success(response.body())
        } else {
            NetworkResponse.Error(response.message(), null,response.code())
        }
    }

}