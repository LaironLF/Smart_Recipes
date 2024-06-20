package com.laironlf.smartRecipes.di

import android.content.Context
import com.laironlf.smartRecipes.data.api.BarcodeApiService
import com.laironlf.smartRecipes.data.api.GS1RusApiService
import com.laironlf.smartRecipes.data.api.RecipesApiService
import com.laironlf.smartRecipes.data.cache.AppCaching
import com.laironlf.smartRecipes.data.implementation.ProductRepositoryImpl
import com.laironlf.smartRecipes.data.implementation.RecipeRepositoryImpl
import com.laironlf.smartRecipes.data.implementation.TechnicalRepositoryImpl
import com.laironlf.smartRecipes.data.repository.BarcodeRepository
import com.laironlf.smartRecipes.domain.repository.ProductRepository
import com.laironlf.smartRecipes.domain.repository.RecipeRepository
import com.laironlf.smartRecipes.domain.repository.TechnicalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideRecipeRepository(appCaching: AppCaching, api: RecipesApiService) : RecipeRepository {
        return RecipeRepositoryImpl(api, appCaching)
    }

    @Provides
    @Singleton
    fun provideProductRepository(appCaching: AppCaching, api: RecipesApiService): ProductRepository {
        return ProductRepositoryImpl(api, appCaching)
    }

    @Provides
    @Singleton
    fun provideTechnicalRepository(
        api: RecipesApiService,
        barcodeApi: BarcodeRepository
    ): TechnicalRepository{
        return TechnicalRepositoryImpl(api, barcodeApi)
    }

}