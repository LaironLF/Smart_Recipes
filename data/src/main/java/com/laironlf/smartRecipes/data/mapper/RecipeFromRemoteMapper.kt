package com.laironlf.smartRecipes.data.mapper

import com.laironlf.smartRecipes.data.dto.IngredientDTO
import com.laironlf.smartRecipes.data.dto.ProductDTO
import com.laironlf.smartRecipes.data.dto.RecipeDTO
import com.laironlf.smartRecipes.data.dto.RecipeStepDTO
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
        matchesProducts = emptyList(),
        imageUrl = imageUrl?:""
    )
}

fun Recipe.mapToDTO(): RecipeDTO = RecipeDTO(
    id = id,
    cookingTime = time,
    idCreatorUser = null,
    ingredients = ingredients.map { it.mapToDTO() },
    kkal = kcal,
    title = title,
    imageUrl = imageUrl
)

fun Ingredient.mapToDTO(): IngredientDTO = IngredientDTO(
    count = count,
    id = product.id,
    measureType = measureType,
    title = product.title
)

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

fun Product.mapToDTO(): ProductDTO = ProductDTO(
    id = id,
    productType = null,
    title = title
)

fun RecipeStepDTO.mapToRecipe(): RecipeStep = RecipeStep(
    step = stepNum?: 0,
    text = stepText?: "",
    image = stepImage
)
