package com.laironlf.smartRecipes.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeasureTypeDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String
)