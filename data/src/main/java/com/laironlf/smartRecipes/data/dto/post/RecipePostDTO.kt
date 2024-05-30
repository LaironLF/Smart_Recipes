package com.laironlf.smartRecipes.data.dto.post


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipePostDTO(
    @SerialName("calories")
    val calories: Int?,
    @SerialName("cookingTime")
    val cookingTime: String?,
    @SerialName("creatorUserId")
    val creatorUserId: Int?,
    @SerialName("imageUrl")
    val imageUrl: String?,
    @SerialName("ingredients")
    val ingredients: List<IngredientPostDTO?>?,
    @SerialName("steps")
    val steps: List<StepPostDTO?>?,
    @SerialName("title")
    val title: String?
)