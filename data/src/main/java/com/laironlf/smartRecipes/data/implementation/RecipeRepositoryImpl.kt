package com.laironlf.smartRecipes.data.implementation

import com.laironlf.smartRecipes.data.api.RecipesApiService
import com.laironlf.smartRecipes.data.cache.AppCaching
import com.laironlf.smartRecipes.data.mapper.mapToDTO
import com.laironlf.smartRecipes.data.mapper.mapToIngredient
import com.laironlf.smartRecipes.data.mapper.mapToRecipeStep
import com.laironlf.smartRecipes.domain.models.params.GetRecipesListParams
import com.laironlf.smartRecipes.domain.models.params.GetRecipesListParams.FetchType
import com.laironlf.smartRecipes.domain.models.Ingredient
import com.laironlf.smartRecipes.domain.models.Recipe
import com.laironlf.smartRecipes.domain.models.RecipeStep
import com.laironlf.smartRecipes.domain.models.params.AddNewRecipeParams
import com.laironlf.smartRecipes.domain.models.params.LikeRecipeParams
import com.laironlf.smartRecipes.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val api: RecipesApiService,
    private val appCache: AppCaching
) : RecipeRepository {


    override suspend fun getRecipesList(params: GetRecipesListParams?): List<Recipe> =
        when (params?.fetchType){
            FetchType.All -> api.getRecipes(null).map { it.mapToRecipeStep() }
            FetchType.Saved -> getSavedRecipesList()
            FetchType.Now -> getFilteredRecipesList(params.productsId!!)
            else -> api.getRecipes(null).map { it.mapToRecipeStep() }
        }


    private suspend fun getSavedRecipesList(): List<Recipe> =
        appCache.getUserRecipeListFromCache().map { it.mapToRecipeStep() }

    private suspend fun getFilteredRecipesList(productIds: List<Int>): List<Recipe> =
        if(productIds.isNotEmpty()){
            api.getRecipes(productIds).map { it.mapToRecipeStep() }
        }  else {
            emptyList()
        }

    override suspend fun getRecipeSteps(id: Int): List<RecipeStep> {
        return api.getRecipeSteps(id).map {
            it.mapToRecipeStep()
        }
    }

    override suspend fun getRecipeIngredients(id: Int): List<Ingredient> {
        return api.getRecipeIngredients(id).map {
            it.mapToIngredient()
        }
    }

    override suspend fun getRecipeById(id: Int): Recipe {
        return api.getRecipe(id).mapToRecipeStep()
    }

    override suspend fun saveRecipeIntoStorage(params: LikeRecipeParams) {
        val recipe = params.recipe.mapToDTO()
        recipe.recipeStepDTO = params.recipeSteps.map { it.mapToDTO() }
        appCache.saveUserRecipeToCache(params.recipe.mapToDTO())
    }

    override suspend fun removeRecipeFromStorage(params: LikeRecipeParams) {
        val recipe = params.recipe.mapToDTO()
        recipe.recipeStepDTO = params.recipeSteps.map { it.mapToDTO() }
        appCache.deleteUserRecipeFromCache(params.recipe.mapToDTO())
    }

    override suspend fun getRecipeFromStorage(idRecipe: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun addNewRecipe(params: AddNewRecipeParams) {
        api.uploadRecipe(params.recipe.mapToDTO())
    }

}