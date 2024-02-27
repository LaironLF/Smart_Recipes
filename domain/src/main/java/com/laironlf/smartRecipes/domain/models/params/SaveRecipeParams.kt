package com.laironlf.smartRecipes.domain.models.params

import com.laironlf.smartRecipes.domain.models.Recipe
import com.laironlf.smartRecipes.domain.models.RecipeStep

data class SaveRecipeParams(
    val recipe: Recipe,
    val saveToUserRecipes: Boolean = false
){
    var recipeSteps: List<RecipeStep> = emptyList()
        internal set
}