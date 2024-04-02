package com.bbasoglu.swipefun.matchmaker.common.data.di

import android.content.Context
import androidx.room.Room
import com.bbasoglu.swipefun.matchmaker.common.data.db.RickMortyDao
import com.bbasoglu.swipefun.matchmaker.common.data.db.RickMortyDatabase
import com.bbasoglu.swipefun.matchmaker.common.data.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RickMortyDatabaseModule {
    @Singleton
    @Provides
    fun provideRickMortyDatabase(@ApplicationContext appContext: Context): RickMortyDatabase {
        return Room.databaseBuilder(
            appContext, RickMortyDatabase::class.java, Constants.RICK_MORTY_DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideRickMortyDao(database: RickMortyDatabase): RickMortyDao {
        return database.rickMortyDao()
    }

}