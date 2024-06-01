package com.laironlf.smartRecipes.domain.usecases.product

import com.laironlf.smartRecipes.domain.models.Product
import com.laironlf.smartRecipes.domain.repository.ProductRepository

class AddProductUserUseCase (
    private val productRepository: ProductRepository
){
    suspend operator fun invoke(product: Product){
        val list = productRepository.getUserProductList()
        if (list.contains(product)) throw ProductAlreadyIsException()
        productRepository.saveUserProduct(product)
    }

    class ProductAlreadyIsException : Exception()

}