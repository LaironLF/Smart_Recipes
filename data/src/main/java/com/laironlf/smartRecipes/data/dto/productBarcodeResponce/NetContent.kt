package com.laironlf.smartRecipes.data.dto.productBarcodeResponce


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetContent(
    @SerialName("codeListVersion")
    val codeListVersion: String?,
    @SerialName("measurementUnitCode")
    val measurementUnitCode: String?,
    @SerialName("value")
    val value: Double?
)