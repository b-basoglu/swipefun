package com.bbasoglu.swipefun.matchmaker.feed.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.bbasoglu.swipefun.matchmaker.common.data.db.RickMortyDao
import com.bbasoglu.swipefun.matchmaker.common.data.db.RickMortyDatabase
import com.bbasoglu.swipefun.matchmaker.common.data.model.response.RickAndMortyCharacterResponseItem
import com.bbasoglu.swipefun.matchmaker.common.data.model.response.RickyMortyCharacterResponseModel
import com.bbasoglu.swipefun.matchmaker.common.data.network.RickyMortyApiHelper
import com.bbasoglu.swipefun.matchmaker.feed.data.CharacterPagingSource
import com.bbasoglu.swipefun.matchmaker.feed.data.RickMortyLikesInsertRepository
import com.bbasoglu.swipefun.matchmaker.feed.data.RickMortyGetCharactersRepository
import com.bbasoglu.swipefun.matchmaker.feed.domain.model.RickAndMortyCharacterDomainModel
import com.bbasoglu.swipefun.matchmaker.feed.domain.repository.RickMortyLikesInsertRepositoryImpl
import com.bbasoglu.swipefun.matchmaker.feed.domain.repository.RickMortyGetCharactersRepositoryImpl
import com.bbasoglu.swipefun.matchmaker.feed.domain.usecase.RickMortyGetCharactersComposeUseCase
import com.bbasoglu.swipefun.matchmaker.feed.domain.usecase.RickMortyGetCharactersUseCase
import com.bbasoglu.swipefun.matchmaker.feed.domain.usecase.RickMortyLikesInsertUseCase
import com.bbasoglu.swipefun.network.NetworkResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class RickMortyUseCaseTests {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockApiHelper = mockk<RickyMortyApiHelper>()

    private lateinit var database: RickMortyDatabase
    private lateinit var dao: RickMortyDao

    private lateinit var characterPagingSource: CharacterPagingSource
    private lateinit var rickMortyRepository: RickMortyGetCharactersRepository
    private lateinit var rickMortyLikesInsertRepository: RickMortyLikesInsertRepository
    private lateinit var rickMortyLikesInsertUseCase: RickMortyLikesInsertUseCase
    private lateinit var rickMortyGetCharactersComposeUseCase: RickMortyGetCharactersComposeUseCase
    private lateinit var rickMortyGetCharactersUseCase: RickMortyGetCharactersUseCase


    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, RickMortyDatabase::class.java).build()
        dao = database.rickMortyDao()
        rickMortyLikesInsertRepository = RickMortyLikesInsertRepositoryImpl(database)
        rickMortyLikesInsertUseCase = RickMortyLikesInsertUseCase(rickMortyLikesInsertRepository, Dispatchers.IO)
        characterPagingSource = CharacterPagingSource(mockApiHelper)
        rickMortyRepository = RickMortyGetCharactersRepositoryImpl(characterPagingSource,mockApiHelper)
        rickMortyGetCharactersComposeUseCase = RickMortyGetCharactersComposeUseCase(rickMortyRepository)
        rickMortyGetCharactersUseCase = RickMortyGetCharactersUseCase(rickMortyRepository)

    }

    @Test
    fun `testExecute`() = runBlocking {
        val character:RickAndMortyCharacterDomainModel = RickAndMortyCharacterDomainModel(1,"Mert",null,null,null,null,null,null,null,null,null,null)
        val param = RickMortyLikesInsertUseCase.Param(character)

        rickMortyLikesInsertUseCase.execute(param)

        TestCase.assertEquals(rickMortyLikesInsertRepository.getRickMortyWithId(1)?.toRickAndMortyDomainModel()?.name, character.name)
    }

    @Test
    fun testRickMortyGetCharactersUseCase() = runBlocking {
        val character = RickAndMortyCharacterDomainModel(1,"Mert",null,null,null,null,null,null,null,null,null,null)
        val character2 = RickAndMortyCharacterDomainModel(2,"Miray",null,null,null,null,null,null,null,null,null,null)

        rickMortyLikesInsertUseCase.execute(RickMortyLikesInsertUseCase.Param(character))
        rickMortyLikesInsertUseCase.execute(RickMortyLikesInsertUseCase.Param(character2))
        Assert.assertEquals(dao.getRowCount().first(), 2)
    }

    @Test
    fun `testGetCharacterRepositoryPaging`() = runBlocking {
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

        val result = rickMortyGetCharactersComposeUseCase.execute(1)

        assertEquals(
            "Rick Sanchez",
            (result as NetworkResponse.Success).data?.rickAndMortyCharacterDomainModelList?.getOrNull(0)?.name
        )
    }

}
