package com.laironlf.smartRecipes.domain.repository

import com.laironlf.smartRecipes.domain.models.params.GetProductListParams
import com.laironlf.smartRecipes.domain.models.Product

interface ProductRepository {
    suspend fun getProductList(params: GetProductListParams?) : List<Product>
//    suspend fun getUserProductList(): List<Product>
    suspend fun saveUserProduct(product: Product): Boolean
    suspend fun deleteUserProduct(product: Product): Boolean
}