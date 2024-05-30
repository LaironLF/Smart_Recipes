package com.laironlf.smartRecipes.data.dto.post


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StepPostDTO(
    @SerialName("imageUrl")
    val imageUrl: String?,
    @SerialName("text")
    val text: String?
)