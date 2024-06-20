package com.laironlf.smartRecipes.data.implementation.BarcodeServices

import com.laironlf.smartRecipes.data.api.BarcodeApiService
import com.laironlf.smartRecipes.data.repository.BarcodeRepository
import com.laironlf.smartRecipes.domain.models.ProductBarcodeData
import org.jsoup.Jsoup

class BarcodeApiServiceImpl(
    private val BarcodeApiService: BarcodeApiService
): BarcodeRepository {
    override suspend fun getProductInfoByBarcode(barcode: String): ProductBarcodeData {
        val response = BarcodeApiService.getData(barcode)
        val html = Jsoup.parse(response.body()!!)
        val company = html.select("p").first()?.ownText()
        val name = html.select("p").last()?.ownText()
        return ProductBarcodeData(
            name = name,
            brandName = company,
            barcode = barcode
        )
    }
}