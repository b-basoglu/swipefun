package com.bbasoglu.swipefun.matchmaker.common.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bbasoglu.swipefun.matchmaker.common.data.model.entity.Converters
import com.bbasoglu.swipefun.matchmaker.common.data.model.entity.RickMortyEntity

@Database(
    entities = [RickMortyEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class RickMortyDatabase : RoomDatabase() {

    abstract fun rickMortyDao(): RickMortyDao
}