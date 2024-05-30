package com.laironlf.smartRecipes.data.dto.post


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IngredientPostDTO(
    @SerialName("measureTypeId")
    val measureTypeId: Int?,
    @SerialName("productId")
    val productId: Int?,
    @SerialName("quantity")
    val quantity: Int?
)