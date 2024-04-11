package com.laironlf.smartRecipes.data.cache

import android.content.Context
import com.laironlf.smartRecipes.data.dto.ProductDTO
import com.laironlf.smartRecipes.data.dto.RecipeDTO
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class AppCaching(
    val context: Context
) {
    private val userProductsList = ObservableList<ProductDTO>()
    private val productsList = ObservableList<ProductDTO>()
    private val userRecipeList = ObservableList<RecipeDTO>()

    init {
        userProductsList.setList(readProductsArray(USER_PRODUCTS_CACHE_NAME))
        userProductsList.setListener {
            writeToCache(USER_PRODUCTS_CACHE_NAME, json.encodeToString(it))
        }

        productsList.setList(readProductsArray(PRODUCTS_CACHE_NAME))
        productsList.setListener {
            writeToCache(PRODUCTS_CACHE_NAME, json.encodeToString(it))
        }

        userRecipeList.setList(readRecipeArray(SAVED_RECIPE_CACHE_NAME))
        userRecipeList.setListener {
            writeToCache(SAVED_RECIPE_CACHE_NAME, json.encodeToString(it))
        }
    }

    private fun readProductsArray(fileName: String): ArrayList<ProductDTO> {
        var array: ArrayList<ProductDTO> = ArrayList()
        val arrayJson = readFromCache(fileName)
        if (arrayJson != "") array = json.decodeFromString(arrayJson)
        return array
    }

    private fun readRecipeArray(fileName: String): ArrayList<RecipeDTO> {
        var array: ArrayList<RecipeDTO> = ArrayList()
        val arrayJson = readFromCache(fileName)
        if (arrayJson != "") array = json.decodeFromString(arrayJson)
        return array
    }



    fun saveUserProductToCache(product: ProductDTO) {
        userProductsList.add(product)
    }
    fun deleteUserProductFromCache(product: ProductDTO) {
        userProductsList.remove(product)
    }
    fun getUserProductList(): List<ProductDTO> = readProductsArray(USER_PRODUCTS_CACHE_NAME)
    fun getProductList(): List<ProductDTO> = readProductsArray(PRODUCTS_CACHE_NAME)
    fun clearProductList() = productsList.clear()
    fun addProductList(products: List<ProductDTO>) {
        products.forEach { productsList.add(it) }
    }


    private fun writeToCache(fileName: String, data: String) {
        val cacheDir: File = context.cacheDir
        val myFile = File(cacheDir, fileName)
        myFile.writeText(data);
    }
    private fun readFromCache(fileName: String): String {
        val cacheDir: File = context.cacheDir
        val myFile = File(cacheDir, fileName)
        return try {
            myFile.readText()
        } catch (e: Exception) {
            ""
        }
    }


    class ObservableList<T> {
        private var _list = ArrayList<T>()
        private var onChanged: (List<T>) -> Unit = {}

        fun setListener(listener: (List<T>) -> Unit){
            onChanged = listener
        }
        fun setList(list: ArrayList<T>) {
            _list = list
        }

        fun add(element: T) {
            _list.add(element)
            onChanged(_list)
        }


        fun remove(element: T) {
            _list.remove(element)
            onChanged(_list)
        }

        fun clear() {
            _list.clear()
            onChanged(_list)
        }


    }

    companion object {
        private val json = Json { prettyPrint = true }
        private const val SAVED_RECIPE_CACHE_NAME = "saved_user_recipes"
        private const val USER_PRODUCTS_CACHE_NAME = "saved_user_products"
        private const val PRODUCTS_CACHE_NAME = "saved_products"
    }
}