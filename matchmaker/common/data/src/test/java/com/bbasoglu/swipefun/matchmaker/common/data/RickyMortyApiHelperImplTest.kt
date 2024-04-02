package com.bbasoglu.swipefun.matchmaker.common.data

import com.bbasoglu.swipefun.matchmaker.common.data.model.response.RickyMortyCharacterResponseModel
import com.bbasoglu.swipefun.matchmaker.common.data.network.RickyMortyApiHelperImpl
import com.bbasoglu.swipefun.matchmaker.common.data.network.RickyMortyApiService
import com.bbasoglu.swipefun.network.NetworkResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody

class RickyMortyApiHelperImplTest {

    @Test
    fun `getCharacters_SuccessfulResponse`() = runBlocking {
        // Given
        val expectedResponseModel = RickyMortyCharacterResponseModel(info = null, results = null)
        val mockApiService = mockk<RickyMortyApiService>()
        coEvery { mockApiService.getCharacters(any()) } returns Response.success(expectedResponseModel)
        val apiHelper = RickyMortyApiHelperImpl(mockApiService)

        // When
        val actualResult = apiHelper.getCharacters(1)

        // Then
        assertEquals(NetworkResponse.Success(expectedResponseModel), actualResult)
    }

    @Test
    fun `getCharacters_ErrorResponse`() = runBlocking {
        // Given
        val mockApiService = mockk<RickyMortyApiService>()
        coEvery { mockApiService.getCharacters(any()) } returns Response.error(
            404,
            "{}".toResponseBody("application/json".toMediaTypeOrNull())
        )
        val apiHelper = RickyMortyApiHelperImpl(mockApiService)

        // When
        val actualResult = apiHelper.getCharacters(1)

        // Then
        assertEquals(NetworkResponse.Error("Response.error()", null, 404), actualResult)
    }
}
