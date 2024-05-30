package com.laironlf.smartRecipes.domain.models.post


data class RecipePost(
    val calories: Int?,
    val cookingTime: String?,
    val creatorUserId: Int?,
    val imageUrl: String?,
    val ingredients: List<IngredientPost>?,
    val steps: List<StepPost>?,
    val title: String?
)