package com.laironlf.smartRecipes.data.dto.productBarcodeResponce


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LastChangeDate(
    @SerialName("day")
    val day: Int?,
    @SerialName("fractionalSecond")
    val fractionalSecond: Double?,
    @SerialName("hour")
    val hour: Int?,
    @SerialName("minute")
    val minute: Int?,
    @SerialName("month")
    val month: Int?,
    @SerialName("second")
    val second: Int?,
    @SerialName("timezone")
    val timezone: Int?,
    @SerialName("year")
    val year: Int?
)