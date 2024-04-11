package com.laironlf.smartRecipes.data.api

import com.laironlf.smartRecipes.data.dto.IngredientDTO
import com.laironlf.smartRecipes.data.dto.ProductDTO
import com.laironlf.smartRecipes.data.dto.RecipeDTO
import com.laironlf.smartRecipes.data.dto.RecipeStepDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipesApiService {

    @GET("recipes/")
    suspend fun getRecipes(
        @Query("products") productsId: List<Int>? = null
    ) : List<RecipeDTO>

    @GET("products/")
    suspend fun getProducts() : List<ProductDTO>

    @GET("recipes/{id}/ingredients")
    suspend fun getRecipeIngredients(
        @Path("id") id: Int
    ): List<IngredientDTO>

    @GET("recipes/{id}/steps")
    suspend fun getRecipeSteps(
        @Path("id") id: Int
    ): List<RecipeStepDTO>

    @GET("recipes/{id}")
    suspend fun getRecipe(
        @Path("id")id: Int
    ) : RecipeDTO
}