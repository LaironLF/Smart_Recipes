package com.laironlf.smartRecipes.domain.repository

import com.laironlf.smartRecipes.domain.models.ProductBarcodeData
import com.laironlf.smartRecipes.domain.models.post.RealProductPost
import java.io.File

interface TechnicalRepository {
    suspend fun uploadImage(file: File): String
    suspend fun getProductBarcodeData(barcode: String): ProductBarcodeData
    suspend fun uploadRealProduct(realProductPost: RealProductPost)

}