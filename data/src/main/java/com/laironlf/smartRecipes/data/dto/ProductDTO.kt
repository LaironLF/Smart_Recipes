package com.laironlf.smartRecipes.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("product_type")
    val productType: String?,
    @SerialName("title")
    val title: String?
)