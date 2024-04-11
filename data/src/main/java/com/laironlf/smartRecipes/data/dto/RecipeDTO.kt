package com.laironlf.smartRecipes.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDTO(
    @SerialName("cooking_time")
    val cookingTime: String?,
    @SerialName("id")
    val id: Int,
    @SerialName("id_CreatorUser")
    val idCreatorUser: Int?,
    @SerialName("ingredients")
    val ingredients: List<IngredientDTO>?,
    @SerialName("kkal")
    val kkal: Int?,
    @SerialName("title")
    val title: String?,
    @SerialName("image_url")
    val imageUrl: String?
)