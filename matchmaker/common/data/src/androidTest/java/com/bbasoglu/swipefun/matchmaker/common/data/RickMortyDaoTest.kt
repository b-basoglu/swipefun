package com.bbasoglu.swipefun.matchmaker.common.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.bbasoglu.swipefun.matchmaker.common.data.db.RickMortyDao
import com.bbasoglu.swipefun.matchmaker.common.data.db.RickMortyDatabase
import com.bbasoglu.swipefun.matchmaker.common.data.model.entity.RickMortyEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RickMortyDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: RickMortyDatabase
    private lateinit var dao: RickMortyDao

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, RickMortyDatabase::class.java).build()
        dao = database.rickMortyDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `insertAndRetrieveSingleRickMortyEntity`() = runBlocking {
        val rickMortyEntity = RickMortyEntity(1, 1,"Rick", "Sanchez",null,null,null,null,null,null,null,null,null)

        dao.insertRickMorty(rickMortyEntity)
        val result = dao.getRickMortyWithId(1)

        assertEquals(rickMortyEntity, result)
    }

    @Test
    fun `insertAndRetrieveMultipleRickMortyEntity`() = runBlocking {
        val rickMortyEntity1 = RickMortyEntity(1,1, "Rick", "Sanchez",null,null,null,null,null,null,null,null,null)
        val rickMortyEntity2 = RickMortyEntity(2,2, "Morty", "Smith",null,null,null,null,null,null,null,null,null)
        val rickMortyEntity3 = RickMortyEntity(3,3, "Summer", "Smith",null,null,null,null,null,null,null,null,null)

        dao.insertRickMorty(rickMortyEntity1)
        dao.insertRickMorty(rickMortyEntity2)
        dao.insertRickMorty(rickMortyEntity3)
        val result = dao.getRickMorty()

        assertEquals(3, result.size)
        assertTrue(result.contains(rickMortyEntity1))
        assertTrue(result.contains(rickMortyEntity2))
        assertTrue(result.contains(rickMortyEntity3))
    }

    @Test
    fun `deleteRickMortyEntity`() = runBlocking {
        val rickMortyEntity = RickMortyEntity(1,1, "Rick", "Sanchez",null,null,null,null,null,null,null,null,null)
        dao.insertRickMorty(rickMortyEntity)

        dao.deleteRickMorty(rickMortyEntity)
        val result = dao.getRickMortyWithId(1)

        assertNull(result)
    }

    @Test
    fun `insertSameRickMortyEntityTwice`() = runBlocking {
        val rickMortyEntity = RickMortyEntity(1,1, "Rick", "Sanchez",null,null,null,null,null,null,null,null,null)

        dao.insertRickMorty(rickMortyEntity)
        dao.insertRickMorty(rickMortyEntity)

        val result = dao.getRickMorty()
        assertEquals(1, result.size)
    }

    @Test
    fun `getRickMortyWithInvalidId`() = runBlocking {
        val result = dao.getRickMortyWithId(999)

        assertNull(result)
    }

    @Test
    fun `deleteNonExistingRickMortyEntity`() = runBlocking {
        val rickMortyEntity = RickMortyEntity(1,1, "Rick", "Sanchez",null,null,null,null,null,null,null,null,null)

        dao.deleteRickMorty(rickMortyEntity)

        val result = dao.getRickMorty()
        assertTrue(result.isEmpty())
    }

    @Test
    fun `getRowCountWithEmptyDatabase`() = runBlocking {
        val count = dao.getRowCount().first()

        assertEquals(0, count)
    }

    @Test
    fun `getRickMortyFlowWithEmptyDatabase`() = runBlocking {
        val flowList = dao.getRickMortyFlow().first()

        assertTrue(flowList.isEmpty())
    }

    @Test
    fun `updateRickMortyEntity`() = runBlocking {
        val rickMortyEntity = RickMortyEntity(1,1, "Rick", "Sanchez",null,null,null,null,null,null,null,null,null)
        dao.insertRickMorty(rickMortyEntity)

        val updatedRickMortyEntity = rickMortyEntity.copy(name = "Updated Rick")
        dao.updateRickMorty(updatedRickMortyEntity)

        val result = dao.getRickMortyWithId(1)
        assertEquals("Updated Rick", result?.name)
    }

    @Test
    fun `getRowCount`() = runBlocking {
        val rickMortyEntity1 = RickMortyEntity(1,1, "Rick", "Sanchez",null,null,null,null,null,null,null,null,null)
        val rickMortyEntity2 = RickMortyEntity(2,2, "Morty", "Smith",null,null,null,null,null,null,null,null,null)
        dao.insertRickMorty(rickMortyEntity1)
        dao.insertRickMorty(rickMortyEntity2)

        val count = dao.getRowCount().first()

        assertEquals(2, count)
    }

    @Test
    fun `getRickMortyFlow`() = runBlocking {
        val rickMortyEntity1 = RickMortyEntity(1,1, "Rick", "Sanchez",null,null,null,null,null,null,null,null,null)
        val rickMortyEntity2 = RickMortyEntity(2,2, "Morty", "Smith",null,null,null,null,null,null,null,null,null)
        dao.insertRickMorty(rickMortyEntity1)
        dao.insertRickMorty(rickMortyEntity2)

        val flowList = dao.getRickMortyFlow().first()

        assertEquals(2, flowList.size)
        assertTrue(flowList.contains(rickMortyEntity1))
        assertTrue(flowList.contains(rickMortyEntity2))
    }

}
