package com.laironlf.smartRecipes.di


import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.laironlf.smartRecipes.data.api.BarcodeApiService
import com.laironlf.smartRecipes.data.api.GS1RusApiService
import com.laironlf.smartRecipes.data.api.RecipesApiService
import com.laironlf.smartRecipes.data.cache.AppCaching
import com.laironlf.smartRecipes.data.repository.BarcodeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
class DataModule {

    private val json = Json {
        isLenient = false
        ignoreUnknownKeys = true

    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { this.setLevel(HttpLoggingInterceptor.Level.BODY) })
        .build()

    @Provides
    @Singleton
    fun provideSmartRecipesApi() : RecipesApiService {
        return Retrofit.Builder()
            .baseUrl("http://185.221.214.178:9999/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(RecipesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppCachig(@ApplicationContext context: Context): AppCaching = AppCaching(context)



//    @Provides
//    @Singleton
//    fun provideRoomDB(){
//
//    }
}