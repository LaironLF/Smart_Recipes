package com.laironlf.smartRecipes.data.implementation.BarcodeServices

import com.laironlf.smartRecipes.data.api.GS1RusApiService
import com.laironlf.smartRecipes.data.mapper.mapToDomain
import com.laironlf.smartRecipes.data.repository.BarcodeRepository
import com.laironlf.smartRecipes.domain.models.ProductBarcodeData

class GS1RusApiServiceImpl(
    private val GS1RusApiService: GS1RusApiService
): BarcodeRepository {


    override suspend fun getProductInfoByBarcode(barcode: String): ProductBarcodeData {
        return GS1RusApiService.getItem(barcode).mapToDomain()
    }
}