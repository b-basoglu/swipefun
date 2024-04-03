package com.bbasoglu.swipefun.matchmaker.common.data.di

import com.bbasoglu.swipefun.core.client.ClientGenerator
import com.bbasoglu.swipefun.di.RawOKHttpInstance
import com.bbasoglu.swipefun.matchmaker.common.data.network.RickyMortyApiHelper
import com.bbasoglu.swipefun.matchmaker.common.data.network.RickyMortyApiHelperImpl
import com.bbasoglu.swipefun.matchmaker.common.data.network.RickyMortyApiService
import com.bbasoglu.swipefun.utils.Constants.NetworkConstants.MAIN_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RickMortyServiceModule {

    @Provides
    @RickMortyRetrofitInstance
    @Singleton
    fun provideRickMortyRetrofit(
        @RawOKHttpInstance okHttpClient: OkHttpClient,
    ): Retrofit =
        ClientGenerator.generateRetrofitClient(
            okHttpClient = okHttpClient,
            baseUrl = MAIN_BASE_URL,
        )


    @Provides
    @RickMortyApiServiceInstance
    @Singleton
    fun provideRickMortyApiService(@RickMortyRetrofitInstance retrofit: Retrofit): RickyMortyApiService =
        retrofit.create(
            RickyMortyApiService::class.java
        )

    @Provides
    @Singleton
    fun provideRickMortyApiHelper(apiHelper: RickyMortyApiHelperImpl): RickyMortyApiHelper =
        apiHelper
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RickMortyRetrofitInstance

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RickMortyApiServiceInstance