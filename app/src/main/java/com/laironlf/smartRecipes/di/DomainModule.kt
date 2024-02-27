package com.laironlf.smartRecipes.di

import android.content.Context
import com.laironlf.smartRecipes.data.api.smartRecipesApi.RecipesApiService
import com.laironlf.smartRecipes.data.implementation.ProductRepositoryImpl
import com.laironlf.smartRecipes.data.implementation.RecipeRepositoryImpl
import com.laironlf.smartRecipes.domain.repository.ProductRepository
import com.laironlf.smartRecipes.domain.repository.RecipeRepository
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
    fun provideRecipeRepository(@ApplicationContext context: Context, api: RecipesApiService) : RecipeRepository {
        return RecipeRepositoryImpl(api, context)
    }

    @Provides
    @Singleton
    fun provideProductRepository(@ApplicationContext context: Context, api: RecipesApiService): ProductRepository {
        return ProductRepositoryImpl(api, context)
    }
}