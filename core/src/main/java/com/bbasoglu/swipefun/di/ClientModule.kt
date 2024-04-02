package com.bbasoglu.swipefun.di

import com.bbasoglu.swipefun.core.client.ClientGenerator
import com.bbasoglu.swipefun.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClientModule {

    @Provides
    @RawOKHttpInstance
    @Singleton
    fun provideRawOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .callTimeout(
            Constants.NetworkConstants.DEFAULT_CALL_TIMEOUT_SEC_FOR_REQUEST,
            TimeUnit.SECONDS
        )
        .connectTimeout(
            Constants.NetworkConstants.DEFAULT_CONNECT_TIMEOUT_SEC_FOR_REQUEST,
            TimeUnit.SECONDS
        )
        .readTimeout(
            Constants.NetworkConstants.DEFAULT_READ_TIMEOUT_SEC_FOR_REQUEST,
            TimeUnit.SECONDS
        )
        .writeTimeout(
            Constants.NetworkConstants.DEFAULT_WRITE_TIMEOUT_SEC_FOR_REQUEST,
            TimeUnit.SECONDS
        ).build()

    @Provides
    @RawRetrofitInstance
    @Singleton
    fun provideSignUpRetrofit(
        @RawOKHttpInstance okHttpClient: OkHttpClient,
    ): Retrofit =
        ClientGenerator.generateRetrofitClient(
            okHttpClient = okHttpClient,
            baseUrl = Constants.NetworkConstants.MAIN_BASE_URL,
        )
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RawOKHttpInstance

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RawRetrofitInstance
