package com.laironlf.smartRecipes.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UploadImageResponseDTO(
    @SerialName("name")
    val name: String?,
    @SerialName("path")
    val path: String?
)