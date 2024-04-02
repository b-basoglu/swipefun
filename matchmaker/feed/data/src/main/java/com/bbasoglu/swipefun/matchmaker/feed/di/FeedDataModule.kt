package com.bbasoglu.swipefun.matchmaker.feed.di

import com.bbasoglu.swipefun.matchmaker.common.data.network.RickyMortyApiHelper
import com.bbasoglu.swipefun.matchmaker.feed.data.CharacterPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeedDataModule {

    @Provides
    @Singleton
    fun provideCharacterPagingSource(rickyMortyApiHelper: RickyMortyApiHelper): CharacterPagingSource = CharacterPagingSource(rickyMortyApiHelper)

}
