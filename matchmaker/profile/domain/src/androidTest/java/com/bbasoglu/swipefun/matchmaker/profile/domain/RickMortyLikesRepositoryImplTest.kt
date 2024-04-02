package com.bbasoglu.swipefun.matchmaker.profile.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.bbasoglu.swipefun.matchmaker.common.data.db.RickMortyDao
import com.bbasoglu.swipefun.matchmaker.common.data.db.RickMortyDatabase
import com.bbasoglu.swipefun.matchmaker.common.data.model.entity.RickMortyEntity
import com.bbasoglu.swipefun.matchmaker.profile.data.repository.RickMortyLikesRepository
import com.bbasoglu.swipefun.matchmaker.profile.domain.repository.RickMortyLikesRepositoryImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class RickMortyLikesRepositoryImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: RickMortyDatabase
    private lateinit var dao: RickMortyDao



    private lateinit var repository: RickMortyLikesRepository

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, RickMortyDatabase::class.java).build()
        dao = database.rickMortyDao()
        repository = RickMortyLikesRepositoryImpl(database)

    }


    @Test
    fun testGetRickMorty() = runBlocking{
        val expectedList = listOf(RickMortyEntity(1,id = 1, name = "Rick",null,null,null,null,null,null,null,null,null,null), RickMortyEntity(2,id = 2, name = "Morty",null,null,null,null,null,null,null,null,null,null))
        expectedList.forEach{
            repository.insertRickMorty(it)
        }

        val list = repository.getRickMortyFlow().first()

        assertEquals(expectedList, list)
    }

    @Test
    fun testGetRowCount() = runBlocking{
        val expectedList = listOf(RickMortyEntity(1,id = 1, name = "Rick",null,null,null,null,null,null,null,null,null,null), RickMortyEntity(2,id = 2, name = "Morty",null,null,null,null,null,null,null,null,null,null))
        expectedList.forEach{
            repository.insertRickMorty(it)
        }
        val size = repository.getRickMortyRowCount().first()

        assertEquals(expectedList.size, size)
    }

    @Test
    fun testGetRickMortyWithId() = runBlocking{
        val expectedList = listOf(RickMortyEntity(1,id = 1, name = "Rick",null,null,null,null,null,null,null,null,null,null), RickMortyEntity(2,id = 2, name = "Morty",null,null,null,null,null,null,null,null,null,null))
        expectedList.forEach{
            repository.insertRickMorty(it)
        }

        val item = repository.getRickMortyWithId(1)
        val item2 = repository.getRickMortyWithId(2)

        assertEquals(expectedList.get(0), item)
        assertEquals(expectedList.get(1), item2)
    }

    @Test
    fun testDeleteRickMorty() = runBlocking {
        val expectedList = listOf(RickMortyEntity(1,id = 1, name = "Rick",null,null,null,null,null,null,null,null,null,null), RickMortyEntity(2,id = 2, name = "Morty",null,null,null,null,null,null,null,null,null,null))
        expectedList.forEach{
            repository.insertRickMorty(it)
        }
        repository.deleteRickMorty(expectedList.get(0))
        val size = repository.getRickMortyRowCount().first()

        assertEquals(size, 1)
    }

}
