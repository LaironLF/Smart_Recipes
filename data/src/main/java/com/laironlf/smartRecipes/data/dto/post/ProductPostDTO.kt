package com.laironlf.smartRecipes.data.dto.post


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductPostDTO(
    @SerialName("id_productType")
    val idProductType: Int?,
    @SerialName("title")
    val title: String?
)