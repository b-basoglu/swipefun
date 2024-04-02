package com.bbasoglu.swipefun.matchmaker.profile.domain.di

import com.bbasoglu.swipefun.matchmaker.common.data.db.RickMortyDatabase
import com.bbasoglu.swipefun.matchmaker.profile.data.repository.RickMortyLikesRepository
import com.bbasoglu.swipefun.matchmaker.profile.domain.repository.RickMortyLikesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RickMortyLikesDomainModule {

    @Provides
    @Singleton
    fun provideRickMortyLikesRepository(
        rickMortyDatabase: RickMortyDatabase
    ): RickMortyLikesRepository = RickMortyLikesRepositoryImpl(rickMortyDatabase)
}