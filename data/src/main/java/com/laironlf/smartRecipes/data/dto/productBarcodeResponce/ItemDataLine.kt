package com.laironlf.smartRecipes.data.dto.productBarcodeResponce


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemDataLine(
    @SerialName("brandName")
    val brandName: String?,
    @SerialName("gepirRequestedKey")
    val gepirRequestedKey: GepirRequestedKey?,
    @SerialName("informationProvider")
    val informationProvider: InformationProvider?,
    @SerialName("itemDataLanguage")
    val itemDataLanguage: ItemDataLanguage?,
    @SerialName("itemName")
    val itemName: String?,
    @SerialName("lastChangeDate")
    val lastChangeDate: LastChangeDate?,
    @SerialName("manufacturer")
    val manufacturer: Manufacturer?,
    @SerialName("netContent")
    val netContent: List<NetContent>?,
    @SerialName("requestedItem")
    val requestedItem: List<RequestedItem>?,
    @SerialName("returnCode")
    val returnCode: ReturnCode?,
    @SerialName("tradeItemUnitDescriptor")
    val tradeItemUnitDescriptor: TradeItemUnitDescriptor?
)