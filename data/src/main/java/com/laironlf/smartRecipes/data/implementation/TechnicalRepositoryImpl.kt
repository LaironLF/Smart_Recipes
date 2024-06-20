package com.laironlf.smartRecipes.data.implementation

import com.laironlf.smartRecipes.data.api.BarcodeApiService
import com.laironlf.smartRecipes.data.api.RecipesApiService
import com.laironlf.smartRecipes.data.mapper.mapToDTO
import com.laironlf.smartRecipes.data.repository.BarcodeRepository
import com.laironlf.smartRecipes.domain.models.ProductBarcodeData
import com.laironlf.smartRecipes.domain.models.post.RealProductPost
import com.laironlf.smartRecipes.domain.repository.TechnicalRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.jsoup.Jsoup
import java.io.File


class TechnicalRepositoryImpl(
    private val api: RecipesApiService,
    private val barcodeApi: BarcodeRepository
): TechnicalRepository {
    override suspend fun uploadImage(file: File): String {
        val requestFile = file.asRequestBody("image/png".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
        val response = api.uploadImage(body)
        return response.path + response.name
    }

    override suspend fun getProductBarcodeData(barcode: String): ProductBarcodeData {
        return barcodeApi.getProductInfoByBarcode(barcode)
    }

    override suspend fun uploadRealProduct(realProductPost: RealProductPost) {
        api.uploadRealProduct(realProductPost.mapToDTO())
    }
}