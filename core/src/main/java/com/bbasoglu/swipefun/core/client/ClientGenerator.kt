package com.bbasoglu.swipefun.core.client

import com.bbasoglu.swipefun.core.BuildConfig
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ClientGenerator {

    private fun modifyHttpClient(
        okHttpClient: OkHttpClient,
        interceptors: List<Interceptor>?,
        protocol: Boolean = false,
        retry: Boolean = false,
        callTimeout: Long?,
        connectTimeout: Long?,
        readTimeout: Long?,
        writeTimeout: Long?,
        connectionPool: ConnectionPool?,
    ): OkHttpClient {
        val builder = okHttpClient.newBuilder()
        interceptors?.forEach {
            builder.addInterceptor(it)
        }
        callTimeout?.let { timeout ->
            builder.callTimeout(
                timeout,
                TimeUnit.SECONDS
            )
        }
        connectionPool?.let {
            builder.connectionPool(it)
        }
        connectTimeout?.let { timeout ->
            builder.connectTimeout(
                timeout,
                TimeUnit.SECONDS
            )
        }
        readTimeout?.let { timeout ->
            builder.readTimeout(
                timeout,
                TimeUnit.SECONDS
            )
        }
        writeTimeout?.let { timeout ->
            builder.writeTimeout(
                timeout,
                TimeUnit.SECONDS
            )
        }

        if (retry) {
            builder.retryOnConnectionFailure(true)
        }

        if (protocol) {
            builder.protocols(listOf(Protocol.HTTP_1_1))
        }

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    fun generateRetrofitClient(
        okHttpClient: OkHttpClient,
        baseUrl: String,
        interceptors: List<Interceptor>? = null,
        protocol: Boolean = false,
        retry: Boolean = false,
        callTimeout: Long? = null,
        connectTimeout: Long? = null,
        readTimeout: Long? = null,
        writeTimeout: Long? = null,
        connectionPool: ConnectionPool? = null,
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(
                modifyHttpClient(
                    okHttpClient,
                    interceptors,
                    protocol,
                    retry,
                    callTimeout,
                    connectTimeout,
                    readTimeout,
                    writeTimeout,
                    connectionPool
                )
            )
            .build()
    }

    fun generateRetrofitClient(
        okHttpClient: OkHttpClient,
        baseUrl: String,
        interceptors: List<Interceptor>? = null,
    ): Retrofit {
        val builder = okHttpClient.newBuilder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(loggingInterceptor)
        }
        interceptors?.forEach {
            builder.addInterceptor(it)
        }
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(builder.build())
            .build()
    }
}
