package com.laironlf.smartRecipes.domain.usecases

import com.laironlf.smartRecipes.domain.models.RecipeStep
import com.laironlf.smartRecipes.domain.repository.RecipeRepository

class GetRecipeStepsUseCase(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(recipeId: Int) : List<RecipeStep>{
        return recipeRepository.getRecipeSteps(recipeId)?: emptyList()
    }
}