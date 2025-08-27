package com.laironlf.smartRecipes.data.mapper

import com.laironlf.smartRecipes.data.dto.IngredientDTO
import com.laironlf.smartRecipes.data.dto.MeasureTypeDTO
import com.laironlf.smartRecipes.data.dto.ProductDTO
import com.laironlf.smartRecipes.data.dto.RecipeDTO
import com.laironlf.smartRecipes.data.dto.RecipeStepDTO
import com.laironlf.smartRecipes.data.dto.post.IngredientPostDTO
import com.laironlf.smartRecipes.data.dto.post.ProductPostDTO
import com.laironlf.smartRecipes.data.dto.post.RealProductPostDTO
import com.laironlf.smartRecipes.data.dto.post.RecipePostDTO
import com.laironlf.smartRecipes.data.dto.post.StepPostDTO
import com.laironlf.smartRecipes.domain.models.Ingredient
import com.laironlf.smartRecipes.domain.models.MeasureType
import com.laironlf.smartRecipes.domain.models.Product
import com.laironlf.smartRecipes.domain.models.Recipe
import com.laironlf.smartRecipes.domain.models.RecipeStep
import com.laironlf.smartRecipes.domain.models.post.IngredientPost
import com.laironlf.smartRecipes.domain.models.post.ProductPost
import com.laironlf.smartRecipes.domain.models.post.RealProductPost
import com.laironlf.smartRecipes.domain.models.post.RecipePost
import com.laironlf.smartRecipes.domain.models.post.StepPost

fun RecipeDTO.mapToRecipeStep(): Recipe{
    return Recipe(
        id = id,
        title = title?:"",
        time = this.cookingTime?:"0",
        kcal = kkal?:0,
        ingredients = ingredients?.map { it.mapToIngredient() } ?: emptyList(),
        matchesProducts = emptyList(),
        imageUrl = imageUrl?:"",
        liked = false
    )
}

fun MeasureTypeDTO.mapToDomain(): MeasureType{
    return MeasureType(
        id = id,
        title = title
    )
}

fun RealProductPost.mapToDTO(): RealProductPostDTO = RealProductPostDTO(
    idProduct = idProduct,
    barcode = barcode,
    description = description,
    title = title
)

fun StepPost.mapToDTO(): StepPostDTO = StepPostDTO(
    imageUrl = imageUrl,
    text = text
)

fun IngredientPost.mapToDTO(): IngredientPostDTO = IngredientPostDTO(
    measureTypeId = measureType.id,
    productId = product.id,
    quantity = quantity
)

fun ProductPost.mapToDTO(): ProductPostDTO = ProductPostDTO(
    idProductType = idProductType,
    title = title
)

fun RecipePost.mapToDTO(): RecipePostDTO = RecipePostDTO(
    calories = calories,
    cookingTime = cookingTime,
    creatorUserId = creatorUserId,
    imageUrl = imageUrl,
    ingredients = ingredients!!.map { it.mapToDTO() },
    steps = steps!!.map { it.mapToDTO() },
    title = title
)

fun Recipe.mapToDTO(): RecipeDTO = RecipeDTO(
    id = id,
    cookingTime = time,
    idCreatorUser = null,
    ingredients = ingredients.map { it.mapToDTO() },
    kkal = kcal,
    title = title,
    imageUrl = imageUrl,
    recipeStepDTO = null,
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

fun RecipeStepDTO.mapToRecipeStep(): RecipeStep = RecipeStep(
    step = stepNum?: 0,
    text = stepText?: "",
    image = stepImage
)

fun RecipeStep.mapToDTO(): RecipeStepDTO = RecipeStepDTO(
    stepImage = image,
    stepNum = step,
    stepText = text
)
