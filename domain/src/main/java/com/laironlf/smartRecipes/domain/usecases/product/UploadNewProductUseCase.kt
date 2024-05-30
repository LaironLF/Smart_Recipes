package com.laironlf.smartRecipes.domain.usecases.product

import com.laironlf.smartRecipes.domain.models.post.ProductPost
import com.laironlf.smartRecipes.domain.repository.ProductRepository

class UploadNewProductUseCase(
    val productRepository: ProductRepository
) {
    suspend operator fun invoke(product: ProductPost){
        productRepository.uploadNewProduct(product)
    }
}