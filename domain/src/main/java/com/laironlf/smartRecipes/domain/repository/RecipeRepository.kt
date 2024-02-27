package com.laironlf.smartRecipes.domain.repository

import com.laironlf.smartRecipes.domain.models.params.GetRecipesListParams
import com.laironlf.smartRecipes.domain.models.Ingredient
import com.laironlf.smartRecipes.domain.models.Recipe
import com.laironlf.smartRecipes.domain.models.RecipeStep
import com.laironlf.smartRecipes.domain.models.params.SaveRecipeParams

interface RecipeRepository {
    suspend fun getRecipesList(params: GetRecipesListParams?): List<Recipe>
    suspend fun getRecipeSteps(id: Int): List<RecipeStep>
    suspend fun getRecipeIngredients(id: Int): List<Ingredient>
    suspend fun getRecipeById(id: Int): Recipe
    suspend fun saveRecipe(params: SaveRecipeParams)
}