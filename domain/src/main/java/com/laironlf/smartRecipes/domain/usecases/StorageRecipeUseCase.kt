package com.laironlf.smartRecipes.domain.usecases

import com.laironlf.smartRecipes.domain.models.params.LikeRecipeParams
import com.laironlf.smartRecipes.domain.repository.RecipeRepository

/***
 * Юз-ейс для лайка - дизлайка рецепта а качестве аргумента принимает [LikeRecipeParams]
 */
class StorageRecipeUseCase(
    private val recipeRepository: RecipeRepository
){
    suspend operator fun invoke(params: LikeRecipeParams){
        if(params.recipeSteps.isEmpty()) recipeRepository.getRecipeSteps(params.recipe.id)
        if(params.recipe.liked) recipeRepository.saveRecipeIntoStorage(params)
        else recipeRepository.removeRecipeFromStorage(params)
    }
}