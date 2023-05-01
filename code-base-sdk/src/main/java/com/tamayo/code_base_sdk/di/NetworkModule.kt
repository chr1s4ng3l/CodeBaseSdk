package com.tamayo.code_base_sdk.di

import com.google.gson.Gson
import com.tamayo.code_base_sdk.data.rest.BaseService
import com.tamayo.code_base_sdk.data.rest.BaseService.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Module that provides network dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    /**
     * Provides a [Retrofit] instance with [BASE_URL] and [GsonConverterFactory]
     * @param okHttpClient the [OkHttpClient] instance used to build [Retrofit]
     * @param gson the [Gson] instance used to build [Retrofit]
     */
    @Provides
    fun provideMoviesService(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    /**
     * Provides an [OkHttpClient] instance with [HttpLoggingInterceptor] and timeout configurations
     * @param loggingInterceptor the [HttpLoggingInterceptor] instance used to log network calls
     */
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    /**
     * Provides an instance of [HttpLoggingInterceptor] with BODY level logging enabled
     */
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    /**
     * Provides a [BaseService] instance using [Retrofit]
     * @param retrofit the [Retrofit] instance used to create the [BaseService] instance
     */
    @Singleton
    @Provides
    fun provideCocktailsApi(retrofit: Retrofit): BaseService =
        retrofit.create(BaseService::class.java)

    /**
     * Provides a [CoroutineDispatcher] instance with IO context
     */
    @Provides
    fun providesGson(): Gson = Gson()

    /**
     * Provides a [Gson] instance
     */
    @Provides
    fun providesDispatcher(): CoroutineDispatcher = Dispatchers.IO


}