package com.laironlf.smartRecipes.data.dto.productBarcodeResponce


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Manufacturer(
    @SerialName("gln")
    val gln: String?,
    @SerialName("partyName")
    val partyName: List<String>?,
    @SerialName("partyRole")
    val partyRole: List<PartyRole>?
)