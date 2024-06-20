package com.laironlf.smartRecipes.data.dto.productBarcodeResponce


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductBarcodeResponce(
    @SerialName("gepirItem")
    val gepirItem: GepirItem?,
    @SerialName("informationProvider")
    val informationProvider: InformationProviderX?,
    @SerialName("responseHeader")
    val responseHeader: ResponseHeader?
)