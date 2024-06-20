package com.laironlf.smartRecipes.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.laironlf.smartRecipes.data.api.BarcodeApiService
import com.laironlf.smartRecipes.data.api.GS1RusApiService
import com.laironlf.smartRecipes.data.implementation.BarcodeServices.GS1RusApiServiceImpl
import com.laironlf.smartRecipes.data.repository.BarcodeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class BarcodeModule {

    private val json = Json {
        isLenient = false
        ignoreUnknownKeys = true

    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            this.setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .build()

    @Provides
    @Singleton
    fun provideGS1RusApiService() : GS1RusApiService {
        return Retrofit.Builder()
            .baseUrl("https://gepir.gs1ru.org/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()
            .create(GS1RusApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideBarcodeApiService() : BarcodeApiService {
        return Retrofit.Builder()
            .baseUrl("https://barkode.site/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(client)
            .build()
            .create(BarcodeApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideBarcodeRepository(
        gs1RusApiService: GS1RusApiService
    ): BarcodeRepository {
        return GS1RusApiServiceImpl(gs1RusApiService)
    }

}