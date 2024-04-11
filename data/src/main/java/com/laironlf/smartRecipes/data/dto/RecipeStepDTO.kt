package com.laironlf.smartRecipes.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeStepDTO(
    @SerialName("step_image")
    val stepImage: String?,
    @SerialName("step_num")
    val stepNum: Int?,
    @SerialName("step_text")
    val stepText: String?
)