package com.laironlf.smartRecipes.domain.usecases

import com.laironlf.smartRecipes.domain.models.params.SaveRecipeParams
import com.laironlf.smartRecipes.domain.repository.RecipeRepository

class SaveRecipeUseCase(
    private val recipeRepository: RecipeRepository
){
    suspend operator fun invoke(params: SaveRecipeParams){
        params.recipeSteps = recipeRepository.getRecipeSteps(params.recipe.id)
        recipeRepository.saveRecipe(params)
    }
}