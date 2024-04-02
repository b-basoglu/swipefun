package com.bbasoglu.swipefun.matchmaker.common.data.network

import com.bbasoglu.swipefun.matchmaker.common.data.model.response.RickyMortyCharacterResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RickyMortyApiService {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int) : Response<RickyMortyCharacterResponseModel>
}