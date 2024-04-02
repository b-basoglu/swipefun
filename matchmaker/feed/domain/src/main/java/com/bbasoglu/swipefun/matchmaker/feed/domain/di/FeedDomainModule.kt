package com.bbasoglu.swipefun.matchmaker.feed.domain.di

import com.bbasoglu.swipefun.matchmaker.common.data.db.RickMortyDatabase
import com.bbasoglu.swipefun.matchmaker.common.data.network.RickyMortyApiHelper
import com.bbasoglu.swipefun.matchmaker.feed.data.CharacterPagingSource
import com.bbasoglu.swipefun.matchmaker.feed.data.RickMortyLikesInsertRepository
import com.bbasoglu.swipefun.matchmaker.feed.data.RickMortyGetCharactersRepository
import com.bbasoglu.swipefun.matchmaker.feed.domain.repository.RickMortyLikesInsertRepositoryImpl
import com.bbasoglu.swipefun.matchmaker.feed.domain.repository.RickMortyGetCharactersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeedDomainModule {

    @Provides
    @Singleton
    fun provideRickMortyGetCharactersRepository(
        characterPagingSource: CharacterPagingSource,
        rickyMortyApiHelper: RickyMortyApiHelper
    ): RickMortyGetCharactersRepository = RickMortyGetCharactersRepositoryImpl(characterPagingSource,rickyMortyApiHelper)

    @Provides
    @Singleton
    fun provideRickMortyLikesInsertRepository(
        database: RickMortyDatabase,
    ): RickMortyLikesInsertRepository = RickMortyLikesInsertRepositoryImpl(database)
}