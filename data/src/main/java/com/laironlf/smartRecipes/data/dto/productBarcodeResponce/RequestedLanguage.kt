package com.laironlf.smartRecipes.data.dto.productBarcodeResponce


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestedLanguage(
    @SerialName("codeListVersion")
    val codeListVersion: String?,
    @SerialName("value")
    val value: String?
)