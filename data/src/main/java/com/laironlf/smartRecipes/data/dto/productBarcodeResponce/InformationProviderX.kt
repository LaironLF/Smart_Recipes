package com.laironlf.smartRecipes.data.dto.productBarcodeResponce


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InformationProviderX(
    @SerialName("name")
    val name: String?,
    @SerialName("url")
    val url: String?
)