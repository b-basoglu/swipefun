package com.bbasoglu.swipefun.matchmaker.common.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bbasoglu.swipefun.matchmaker.common.data.model.entity.RickMortyEntity
import com.bbasoglu.swipefun.matchmaker.common.data.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface RickMortyDao {

    @Query("SELECT * FROM " + Constants.TABLE_RICK_MORTY_RESULTS + " ORDER BY dataIndex DESC")
    fun getRickMortyPaging(): PagingSource<Int, RickMortyEntity>

    @Query("SELECT * FROM " + Constants.TABLE_RICK_MORTY_RESULTS + " ORDER BY dataIndex ASC")
    fun getRickMorty(): List<RickMortyEntity>

    @Query("SELECT * FROM " + Constants.TABLE_RICK_MORTY_RESULTS + " WHERE id =:id")
    fun getRickMortyWithId(id: Int?): RickMortyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRickMorty(rickMortyEntity: RickMortyEntity)

    @Delete
    fun deleteRickMorty(rickMortyEntity: RickMortyEntity)

    @Update
    fun updateRickMorty(rickMortyEntity: RickMortyEntity)

    @Query("SELECT COUNT(*) FROM "+ Constants.TABLE_RICK_MORTY_RESULTS)
    fun getRowCount(): Flow<Int>

    @Query("SELECT * FROM "+ Constants.TABLE_RICK_MORTY_RESULTS +" ORDER BY dataIndex ASC")
    fun getRickMortyFlow() : Flow<List<RickMortyEntity>>
}