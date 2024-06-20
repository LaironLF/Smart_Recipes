package com.laironlf.smartRecipes.domain.repository

import com.laironlf.smartRecipes.domain.models.MeasureType
import com.laironlf.smartRecipes.domain.models.params.GetProductListParams
import com.laironlf.smartRecipes.domain.models.Product
import com.laironlf.smartRecipes.domain.models.post.ProductPost

interface ProductRepository {
    suspend fun getProductList() : List<Product>
    suspend fun getUserProductList(): List<Product>
    suspend fun saveUserProduct(product: Product): Boolean
    suspend fun deleteUserProduct(product: Product): Boolean

    suspend fun uploadNewProduct(product: ProductPost)

    suspend fun getMeasureTypes(): List<MeasureType>
    suspend fun getProductByBarcode(barcode: String): Product?
}