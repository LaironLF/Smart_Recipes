package com.laironlf.smartRecipes.data.dto.productBarcodeResponce


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestedItem(
    @SerialName("fileFormatName")
    val fileFormatName: FileFormatName?,
    @SerialName("uniformResourceIdentifier")
    val uniformResourceIdentifier: String?
)