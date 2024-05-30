package com.laironlf.smartRecipes.domain.usecases.product

import com.laironlf.smartRecipes.domain.models.MeasureType
import com.laironlf.smartRecipes.domain.repository.ProductRepository

class GetMeasureTypesUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): List<MeasureType>{
        return productRepository.getMeasureTypes()
    }
}