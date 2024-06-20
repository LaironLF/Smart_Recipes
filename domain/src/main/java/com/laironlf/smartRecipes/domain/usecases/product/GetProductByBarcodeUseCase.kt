package com.laironlf.smartRecipes.domain.usecases.product

import com.laironlf.smartRecipes.domain.models.Product
import com.laironlf.smartRecipes.domain.repository.ProductRepository

class GetProductByBarcodeUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(barcode: String): Product?{
        return productRepository.getProductByBarcode(barcode)
    }
}