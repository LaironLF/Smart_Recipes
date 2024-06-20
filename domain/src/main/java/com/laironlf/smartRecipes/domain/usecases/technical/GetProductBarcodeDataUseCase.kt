package com.laironlf.smartRecipes.domain.usecases.technical

import com.laironlf.smartRecipes.domain.models.ProductBarcodeData
import com.laironlf.smartRecipes.domain.repository.TechnicalRepository

class GetProductBarcodeDataUseCase(
    private val technicalRepository: TechnicalRepository
) {
    suspend operator fun invoke(barcode: String): ProductBarcodeData{
        return technicalRepository.getProductBarcodeData(barcode)
    }
}