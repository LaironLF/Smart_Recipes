package com.laironlf.smartRecipes.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IngredientDTO(
    @SerialName("count")
    val count: Int?,
    @SerialName("id")
    val id: Int,
    @SerialName("measureType")
    val measureType: String?,
    @SerialName("title")
    val title: String?
)