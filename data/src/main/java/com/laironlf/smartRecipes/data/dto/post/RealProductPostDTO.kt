package com.laironlf.smartRecipes.data.dto.post


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RealProductPostDTO(
    @SerialName("barcode")
    val barcode: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("id_Product")
    val idProduct: Int?,
    @SerialName("title")
    val title: String?
)