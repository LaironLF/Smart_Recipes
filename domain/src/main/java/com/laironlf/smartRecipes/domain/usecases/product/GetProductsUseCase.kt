package com.laironlf.smartRecipes.domain.usecases.product

import com.laironlf.smartRecipes.domain.models.params.GetProductListParams
import com.laironlf.smartRecipes.domain.models.Product
import com.laironlf.smartRecipes.domain.models.params.GetProductListParams.FetchType
import com.laironlf.smartRecipes.domain.repository.ProductRepository

class GetProductsUseCase (
    private val productRepository: ProductRepository
){
    suspend operator fun invoke(params: GetProductListParams): List<Product>{

        val productList = when (params.fetchType){
            FetchType.UserProducts -> productRepository.getUserProductList()
            FetchType.AllRemoteProducts -> productRepository.getProductList()
            FetchType.AllRemoteProductsExceptUserProducts -> {
                val allProducts: MutableList<Product> = productRepository.getProductList().toMutableList()
                allProducts.removeAll(productRepository.getUserProductList())
                allProducts
            }
            else -> emptyList()
        }
        if(params.searchString != null && params.searchString != "")
            return productList.filter { it.title.lowercase().contains(params.searchString.lowercase()) }

        return productList
    }


    private fun filterByMatchingString(
        products: List<Product>,
        string: String
    ): List<Product> {
        return products.filter { it.title.contains(string) }
    }
}