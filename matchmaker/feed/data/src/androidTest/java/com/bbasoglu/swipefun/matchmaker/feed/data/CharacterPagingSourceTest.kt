package com.bbasoglu.swipefun.matchmaker.feed.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.bbasoglu.swipefun.matchmaker.common.data.model.response.RickAndMortyCharacterResponseItem
import com.bbasoglu.swipefun.matchmaker.common.data.model.response.RickyMortyCharacterResponseModel
import com.bbasoglu.swipefun.matchmaker.common.data.network.RickyMortyApiHelper
import com.bbasoglu.swipefun.network.NetworkResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class CharacterPagingSourceTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockApiHelper = mockk<RickyMortyApiHelper>()

    @Test
    fun loadCharactersSuccessfulResponse() = runBlocking {
        val pagingSource = CharacterPagingSource(mockApiHelper)
        val params = PagingSource.LoadParams.Refresh(1, 20, false)
        val expectedResponse = Response.success(
            RickyMortyCharacterResponseModel(
                info = null,
                results = listOf(
                    RickAndMortyCharacterResponseItem(
                        id = 1,
                        name = "Rick Sanchez",
                        status = "Alive",
                        species = "Human",
                        type = null,
                        gender = "Male",
                        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                        origin = null,
                        location = null,
                        episode = null,
                        url = null,
                        created = null
                    )
                )
            )
        )
        coEvery { mockApiHelper.getCharacters(1) } returns NetworkResponse.Success(expectedResponse.body())

        val result = pagingSource.load(params)

        assertEquals(
            PagingSource.LoadResult.Page(
                data = expectedResponse.body()?.results.orEmpty(),
                prevKey = null,
                nextKey = null
            ),
            result
        )
    }

    @Test
    fun loadCharactersErrorResponse() = runBlocking {
        val pagingSource = CharacterPagingSource(mockApiHelper)
        val params = PagingSource.LoadParams.Refresh(1, 20, false)

        coEvery { mockApiHelper.getCharacters(1) } returns NetworkResponse.Error("Response failed", null, 404)

        val result:PagingSource.LoadResult<Int, RickAndMortyCharacterResponseItem> = pagingSource.load(params)

        assertEquals(
            (result as PagingSource.LoadResult.Error<Int, RickAndMortyCharacterResponseItem>).throwable.message,
            "Response failed"
        )
    }
}
