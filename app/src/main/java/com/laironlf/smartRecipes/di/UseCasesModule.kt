package com.laironlf.smartRecipes.di

import com.laironlf.smartRecipes.domain.repository.ProductRepository
import com.laironlf.smartRecipes.domain.repository.RecipeRepository
import com.laironlf.smartRecipes.domain.repository.TechnicalRepository
import com.laironlf.smartRecipes.domain.usecases.product.AddProductUserUseCase
import com.laironlf.smartRecipes.domain.usecases.recipe.AddRecipeUseCase
import com.laironlf.smartRecipes.domain.usecases.product.GetMeasureTypesUseCase
import com.laironlf.smartRecipes.domain.usecases.product.GetProductsUseCase
import com.laironlf.smartRecipes.domain.usecases.recipe.GetRecipeIngredientsUseCase
import com.laironlf.smartRecipes.domain.usecases.recipe.GetRecipeListUseCase
import com.laironlf.smartRecipes.domain.usecases.recipe.GetRecipeStepsUseCase
import com.laironlf.smartRecipes.domain.usecases.recipe.GetRecipeUseCase
import com.laironlf.smartRecipes.domain.usecases.recipe.StorageRecipeUseCase
import com.laironlf.smartRecipes.domain.usecases.technical.UploadImageUseCase
import com.laironlf.smartRecipes.domain.usecases.product.UploadNewProductUseCase
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
    ): GetRecipeListUseCase {
        return GetRecipeListUseCase(recipeRepository, productRepository)
    }

    @Provides
    @Singleton
    fun provideGetRecipeIngredientsUseCase(
        recipeRepository: RecipeRepository
    ): GetRecipeIngredientsUseCase {
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
    ): GetRecipeUseCase {
        return GetRecipeUseCase(recipeRepository, productRepository)
    }

    @Provides
    @Singleton
    fun provideGetProductsUseCase(
        productRepository: ProductRepository
    ) : GetProductsUseCase {
        return GetProductsUseCase(productRepository)
    }

    @Provides
    @Singleton
    fun provideSaveRecipeUseCase(
        recipeRepository: RecipeRepository
    ) : StorageRecipeUseCase {
        return StorageRecipeUseCase(recipeRepository)
    }

    @Provides
    @Singleton
    fun provideAddRecipeUseCase(
        recipeRepository: RecipeRepository
    ) : AddRecipeUseCase {
        return AddRecipeUseCase(recipeRepository)
    }

    @Provides
    @Singleton
    fun provideGetMeasureTypes(
        productRepository: ProductRepository
    ) : GetMeasureTypesUseCase {
        return GetMeasureTypesUseCase(productRepository)
    }

    @Provides
    @Singleton
    fun provideUploadFileUseCase(
        technicalRepository: TechnicalRepository
    ) : UploadImageUseCase {
        return UploadImageUseCase(technicalRepository)
    }

    @Provides
    @Singleton
    fun provideUploadProductUseCase(
        productRepository: ProductRepository
    ): UploadNewProductUseCase {
        return UploadNewProductUseCase(productRepository)
    }

    @Provides
    @Singleton
    fun provideAddProductUserUseCase(
        productRepository: ProductRepository
    ): AddProductUserUseCase{
        return AddProductUserUseCase(productRepository)
    }
}