package com.laironlf.smartRecipes.data.dto.productBarcodeResponce


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GepirRequestedKey(
    @SerialName("requestedKeyCode")
    val requestedKeyCode: RequestedKeyCode?,
    @SerialName("requestedKeyValue")
    val requestedKeyValue: String?,
    @SerialName("requestedLanguage")
    val requestedLanguage: RequestedLanguage?
)