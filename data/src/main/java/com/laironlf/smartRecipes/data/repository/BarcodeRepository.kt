package com.laironlf.smartRecipes.data.repository

import com.laironlf.smartRecipes.domain.models.ProductBarcodeData

interface BarcodeRepository {
    suspend fun getProductInfoByBarcode(barcode: String): ProductBarcodeData
}