package com.laironlf.smartRecipes.data.dto.productBarcodeResponce


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseHeader(
    @SerialName("gepirReturnCode")
    val gepirReturnCode: GepirReturnCode?,
    @SerialName("numberOfHits")
    val numberOfHits: Int?,
    @SerialName("responderGLN")
    val responderGLN: String?
)