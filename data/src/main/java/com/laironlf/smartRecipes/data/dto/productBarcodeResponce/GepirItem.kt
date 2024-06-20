package com.laironlf.smartRecipes.data.dto.productBarcodeResponce


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GepirItem(
    @SerialName("itemDataLine")
    val itemDataLine: List<ItemDataLine>?
)