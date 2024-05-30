package com.laironlf.smartRecipes.domain.models.params

import com.laironlf.smartRecipes.domain.models.Recipe
import com.laironlf.smartRecipes.domain.models.RecipeStep

data class LikeRecipeParams(
    val recipe: Recipe
){
    var recipeSteps: List<RecipeStep> = emptyList()
        internal set
}