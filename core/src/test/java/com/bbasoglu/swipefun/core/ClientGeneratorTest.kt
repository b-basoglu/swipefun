package com.bbasoglu.swipefun.core

import com.bbasoglu.swipefun.core.client.ClientGenerator
import com.bbasoglu.swipefun.utils.Constants
import junit.framework.TestCase.assertEquals
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class ClientGeneratorTest {

    private lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Test
    fun `test generateRetrofitClient with default values`() {
        val client = ClientGenerator.generateRetrofitClient(okHttpClient, "https://example.com")
        assertEquals("https://example.com/", client.baseUrl().toString())
    }

    @Test
    fun `test generateRetrofitClient with custom interceptors`() {
        val interceptor = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                // do something
                return chain.proceed(chain.request())
            }
        }

        val client = ClientGenerator.generateRetrofitClient(
            okHttpClient,
            "https://example.com",
            listOf(interceptor)
        )
        assertEquals("https://example.com/", client.baseUrl().toString())
    }

    @Test
    fun `test generateRetrofitClient with custom timeout values`() {
        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        // Call the provided endpoint using Retrofit
        val retrofit = ClientGenerator.generateRetrofitClient(
            okHttpClient,
            "https://660bae55ccda4cbc75dd65c0.mockapi.io/mock/"
        )


        // Assert the timeout values of the OkHttpClient
        val client = retrofit.callFactory() as OkHttpClient
        assertEquals(30*1000, client.callTimeoutMillis)
        assertEquals(10*1000, client.connectTimeoutMillis)
        assertEquals(20*1000, client.readTimeoutMillis)
        assertEquals(15*1000, client.writeTimeoutMillis)
    }

    @Test
    fun `test generateRetrofitClient with custom OkHttpClient`() {
        val customClient = OkHttpClient.Builder()
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
            ).addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    // do something
                    return chain.proceed(chain.request())
                }
            })
            .build()
        val client = ClientGenerator.generateRetrofitClient(
            customClient,
            "https://example.com",
            null,
            protocol = true,
            retry = true,
            callTimeout = 30,
            connectTimeout = 10,
            readTimeout = 20,
            writeTimeout = 15,
            connectionPool = ConnectionPool(10, 5, TimeUnit.SECONDS)
        )
        val clientOkHttp = client.callFactory() as OkHttpClient
        assertEquals(30*1000,clientOkHttp.callTimeoutMillis)
        assertEquals(10*1000, clientOkHttp.connectTimeoutMillis)
        assertEquals(20*1000, clientOkHttp.readTimeoutMillis)
        assertEquals(15*1000, clientOkHttp.writeTimeoutMillis)


        // check if the custom interceptor is added
        val interceptors = (client.callFactory() as OkHttpClient).interceptors
        assertEquals(
            2,
            interceptors.size
        )
    }
}
