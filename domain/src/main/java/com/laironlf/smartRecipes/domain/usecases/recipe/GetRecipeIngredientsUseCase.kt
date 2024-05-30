package com.laironlf.smartRecipes.domain.usecases.recipe

import com.laironlf.smartRecipes.domain.models.Ingredient
import com.laironlf.smartRecipes.domain.repository.RecipeRepository

class GetRecipeIngredientsUseCase(
    private val recipeRepository: RecipeRepository
) {

    suspend operator fun invoke(recipeId: Int): List<Ingredient>{
        return recipeRepository.getRecipeIngredients(recipeId)
    }

}