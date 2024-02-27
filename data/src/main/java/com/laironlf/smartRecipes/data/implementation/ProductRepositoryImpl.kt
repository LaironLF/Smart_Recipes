package com.laironlf.smartRecipes.data.implementation

import android.content.Context
import com.laironlf.smartRecipes.data.api.smartRecipesApi.RecipesApiService
import com.laironlf.smartRecipes.data.mapper.mapToDB
import com.laironlf.smartRecipes.data.mapper.mapToProduct
import com.laironlf.smartRecipes.data.room.AppRecipesDatabase
import com.laironlf.smartRecipes.data.room.entity.UserProductDB
import com.laironlf.smartRecipes.domain.models.params.GetProductListParams
import com.laironlf.smartRecipes.domain.models.Product
import com.laironlf.smartRecipes.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val api: RecipesApiService,
    private val context: Context
): ProductRepository {
    private val roomDBDao = AppRecipesDatabase.getDataBase(context).getRoomDBDao()


    override suspend fun getProductList(params: GetProductListParams?): List<Product> {

        val productList = if (params?.fetchUserProducts == true){
            roomDBDao.getUserProducts().map { it.mapToProduct() }
        } else {
            getAllProductList(params)
        }
        return productList
    }

    private suspend fun getAllProductList(params: GetProductListParams?): List<Product>{
        var productList: List<Product>
        try {
            productList = api.getProducts().map { it.mapToProduct() }
            roomDBDao.clearProductList()
            roomDBDao.addProductList(productList.map { it.mapToDB() })
        } catch (e: Exception){
            productList = roomDBDao.getProductList().map { it.mapToProduct() }
        }
        return productList
    }

    override suspend fun saveUserProduct(product: Product): Boolean {
        if(roomDBDao.getProductById(product.id) == null)
            roomDBDao.addProduct(product.mapToDB())
        if(roomDBDao.getUserProductById(product.id) == null)
            roomDBDao.addProductToUserList(UserProductDB(0, product.id))
        return true
    }

    override suspend fun deleteUserProduct(product: Product): Boolean {
        roomDBDao.deleteProductFromUserList(product.id)
        return true
    }
}