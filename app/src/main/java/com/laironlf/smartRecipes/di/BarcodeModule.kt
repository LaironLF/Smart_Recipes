package com.laironlf.smartRecipes.di

import com.laironlf.smartRecipes.data.api.EanOnlineApiService
import com.laironlf.smartRecipes.data.implementation.barcodeServices.EanOnlineApiServiceImpl
import com.laironlf.smartRecipes.data.repository.BarcodeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class BarcodeModule {

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            this.setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .build()

    @Provides
    @Singleton
    fun provideEanOnlineApiService() : EanOnlineApiService {
        return Retrofit.Builder()
            .baseUrl("https://ean-online.ru/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(client)
            .build()
            .create(EanOnlineApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideBarcodeRepository(
        eanOnlineApiService: EanOnlineApiService
    ): BarcodeRepository {
        return EanOnlineApiServiceImpl(eanOnlineApiService)
    }

}