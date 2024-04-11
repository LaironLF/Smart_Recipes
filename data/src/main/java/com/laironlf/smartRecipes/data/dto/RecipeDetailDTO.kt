package com.laironlf.smartRecipes.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDetailDTO(
    @SerialName("cooking_time")
    val cookingTime: String?,
    @SerialName("id")
    val id: Int,
    @SerialName("id_CreatorUser")
    val idCreatorUser: Int?,
    @SerialName("ingredients")
    val ingredients: List<IngredientDTO?>?,
    @SerialName("kkal")
    val kkal: Int?,
    @SerialName("recipe_steps")
    val recipeSteps: List<RecipeStepDTO?>?,
    @SerialName("title")
    val title: String?
)