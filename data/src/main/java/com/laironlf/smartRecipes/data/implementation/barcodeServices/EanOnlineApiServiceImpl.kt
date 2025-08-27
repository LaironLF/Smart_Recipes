package com.laironlf.smartRecipes.data.implementation.barcodeServices

import com.laironlf.smartRecipes.data.api.EanOnlineApiService
import com.laironlf.smartRecipes.data.repository.BarcodeRepository
import com.laironlf.smartRecipes.domain.models.ProductBarcodeData

class EanOnlineApiServiceImpl(
    val eanOnlineApiService: EanOnlineApiService
): BarcodeRepository {
    override suspend fun getProductInfoByBarcode(barcode: String): ProductBarcodeData {
        val res = eanOnlineApiService.getProduct(barcode)
        return ProductBarcodeData(
            name = res,
            brandName = "",
            barcode = barcode
        )
    }

}