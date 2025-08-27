package com.laironlf.smartRecipes.data.implementation

import com.laironlf.smartRecipes.data.api.RecipesApiService
import com.laironlf.smartRecipes.data.cache.AppCaching
import com.laironlf.smartRecipes.data.mapper.mapToDTO
import com.laironlf.smartRecipes.data.mapper.mapToDomain
import com.laironlf.smartRecipes.data.mapper.mapToProduct
import com.laironlf.smartRecipes.domain.models.MeasureType
import com.laironlf.smartRecipes.domain.models.Product
import com.laironlf.smartRecipes.domain.models.params.GetProductListParams
import com.laironlf.smartRecipes.domain.models.post.ProductPost
import com.laironlf.smartRecipes.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val api: RecipesApiService,
    private val appCache: AppCaching
): ProductRepository {


    override suspend fun getProductList(): List<Product> {
        return getAllProductList()
    }

    override suspend fun getUserProductList(): List<Product> {
        return appCache.getUserProductList().map { it.mapToProduct() }
    }

    private suspend fun getAllProductList(): List<Product>{
        var productList: List<Product>
        try {
            productList = api.getProducts().map { it.mapToProduct() }
            appCache.clearProductList()
            appCache.addProductList(productList.map { it.mapToDTO() })
        } catch (e: Exception){
            productList = appCache.getProductList().map { it.mapToProduct() }
        }
        return productList
    }

    override suspend fun saveUserProduct(product: Product): Boolean {
//        if(roomDBDao.getProductById(product.id) == null)
//            roomDBDao.addProduct(product.mapToDB())
//        if(roomDBDao.getUserProductById(product.id) == null)
//            roomDBDao.addProductToUserList(UserProductDB(0, product.id))
        appCache.saveUserProductToCache(product.mapToDTO())
        return true
    }

    override suspend fun deleteUserProduct(product: Product): Boolean {
//        roomDBDao.deleteProductFromUserList(product.id)
        appCache.deleteUserProductFromCache(product.mapToDTO())
        return true
    }

    override suspend fun uploadNewProduct(product: ProductPost) {
        val list = listOf(product)
        api.uploadProduct(list.map { it.mapToDTO() })
    }

    override suspend fun getMeasureTypes(): List<MeasureType> {
        return api.getMeasureTypes().map { it.mapToDomain() }
    }

    override suspend fun getProductByBarcode(barcode: String): Product? {
        val response = api.getProductByBarcode(barcode)
        if (response.isSuccessful && response.body() != null) return response.body()!!.mapToProduct()
        return null
    }
}