package com.laironlf.smartRecipes.domain.usecases

import com.laironlf.smartRecipes.domain.models.params.GetProductListParams
import com.laironlf.smartRecipes.domain.models.Product
import com.laironlf.smartRecipes.domain.repository.ProductRepository

class GetProductsUseCase (
    private val productRepository: ProductRepository
){
    suspend operator fun invoke(params: GetProductListParams): List<Product>{
        var result: List<Product> = emptyList()
        if (params.fetchUserProducts){
           result = productRepository.getProductList(params)
        } else {
            val products = ArrayList(productRepository.getProductList(params))
            val userProducts = productRepository.getProductList(GetProductListParams(fetchUserProducts = true))
            products.removeAll(userProducts.toSet())
            result = products
        }
        return result
    }
}