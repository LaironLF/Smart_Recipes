package com.laironlf.smartRecipes.domain.usecases

import com.laironlf.smartRecipes.domain.models.Recipe
import com.laironlf.smartRecipes.domain.models.params.AddNewRecipeParams
import com.laironlf.smartRecipes.domain.models.post.RecipePost
import com.laironlf.smartRecipes.domain.repository.RecipeRepository


class AddRecipeUseCase(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(params: AddNewRecipeParams){
        recipeRepository.addNewRecipe(params)
    }

    suspend operator fun invoke(recipe: RecipePost){
        recipeRepository.addNewRecipe(AddNewRecipeParams(recipe))
    }
}