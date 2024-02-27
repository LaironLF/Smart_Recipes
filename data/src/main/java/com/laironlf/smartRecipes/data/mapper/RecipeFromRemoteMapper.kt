package com.laironlf.smartRecipes.data.mapper

import com.laironlf.smartRecipes.data.api.smartRecipesApi.dto.IngredientDTO
import com.laironlf.smartRecipes.data.api.smartRecipesApi.dto.ProductDTO
import com.laironlf.smartRecipes.data.api.smartRecipesApi.dto.RecipeDTO
import com.laironlf.smartRecipes.data.api.smartRecipesApi.dto.RecipeStepDTO
import com.laironlf.smartRecipes.domain.models.Ingredient
import com.laironlf.smartRecipes.domain.models.Product
import com.laironlf.smartRecipes.domain.models.Recipe
import com.laironlf.smartRecipes.domain.models.RecipeStep

fun RecipeDTO.mapToRecipe(): Recipe{
    return Recipe(
        id = id,
        title = title?:"",
        time = this.cookingTime?:"0",
        kcal = kkal?:0,
        ingredients = ingredients?.map { it.mapToIngredient() } ?: emptyList(),
        matchesProducts = emptyList()
    )
}

fun IngredientDTO.mapToIngredient(): Ingredient {
    return Ingredient(
        product = Product(id, title?:""),
        count = count?:0,
        measureType = measureType?:""
    )
}

fun ProductDTO.mapToProduct(): Product = Product(
    id = id,
    title = title?: ""
)

fun RecipeStepDTO.mapToRecipe(): RecipeStep = RecipeStep(
    step = stepNum?: 0,
    text = stepText?: "",
    image = stepImage
)
