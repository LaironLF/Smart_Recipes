package com.laironlf.smartRecipes.di

import com.laironlf.smartRecipes.domain.repository.ProductRepository
import com.laironlf.smartRecipes.domain.repository.RecipeRepository
import com.laironlf.smartRecipes.domain.usecases.GetProductsUseCase
import com.laironlf.smartRecipes.domain.usecases.GetRecipeIngredientsUseCase
import com.laironlf.smartRecipes.domain.usecases.GetRecipeListUseCase
import com.laironlf.smartRecipes.domain.usecases.GetRecipeStepsUseCase
import com.laironlf.smartRecipes.domain.usecases.GetRecipeUseCase
import com.laironlf.smartRecipes.domain.usecases.SaveRecipeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Provides
    @Singleton
    fun provideGetRecipeListUseCase(
        recipeRepository: RecipeRepository,
        productRepository: ProductRepository
    ): GetRecipeListUseCase{
        return GetRecipeListUseCase(recipeRepository, productRepository)
    }

    @Provides
    @Singleton
    fun provideGetRecipeIngredientsUseCase(
        recipeRepository: RecipeRepository
    ): GetRecipeIngredientsUseCase{
        return GetRecipeIngredientsUseCase(recipeRepository)
    }

    @Provides
    @Singleton
    fun provideGetRecipeStepsUseCase(
        recipeRepository: RecipeRepository
    ): GetRecipeStepsUseCase {
        return GetRecipeStepsUseCase(recipeRepository)
    }

    @Provides
    @Singleton
    fun provideGetRecipeUseCase(
        recipeRepository: RecipeRepository,
        productRepository: ProductRepository
    ): GetRecipeUseCase{
        return GetRecipeUseCase(recipeRepository, productRepository)
    }

    @Provides
    @Singleton
    fun provideGetProductsUseCase(
        productRepository: ProductRepository
    ) : GetProductsUseCase{
        return GetProductsUseCase(productRepository)
    }

    @Provides
    @Singleton
    fun provideSaveRecipeUseCase(
        recipeRepository: RecipeRepository
    ) : SaveRecipeUseCase{
        return SaveRecipeUseCase(recipeRepository)
    }

}