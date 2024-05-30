package com.laironlf.smartRecipes.data.implementation

import com.laironlf.smartRecipes.data.api.RecipesApiService
import com.laironlf.smartRecipes.domain.repository.TechnicalRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class TechnicalRepositoryImpl(
    private val api: RecipesApiService
): TechnicalRepository {
    override suspend fun uploadImage(file: File): String {
        val requestFile = file.asRequestBody("image/png".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
        val response = api.uploadImage(body)
        return response.path + response.name
    }
}