package com.laironlf.smartRecipes.data.api

import com.laironlf.smartRecipes.data.dto.IngredientDTO
import com.laironlf.smartRecipes.data.dto.MeasureTypeDTO
import com.laironlf.smartRecipes.data.dto.ProductDTO
import com.laironlf.smartRecipes.data.dto.RecipeDTO
import com.laironlf.smartRecipes.data.dto.RecipeStepDTO
import com.laironlf.smartRecipes.data.dto.UploadImageResponseDTO
import com.laironlf.smartRecipes.data.dto.post.ProductPostDTO
import com.laironlf.smartRecipes.data.dto.post.RealProductPostDTO
import com.laironlf.smartRecipes.data.dto.post.RecipePostDTO
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipesApiService {

    @GET("recipes/")
    suspend fun getRecipes(
        @Query("products") productsId: List<Int>? = null
    ) : List<RecipeDTO>

    @GET("products/")
    suspend fun getProducts() : List<ProductDTO>

    @GET("recipes/{id}/ingredients")
    suspend fun getRecipeIngredients(
        @Path("id") id: Int
    ): List<IngredientDTO>

    @GET("recipes/{id}/steps")
    suspend fun getRecipeSteps(
        @Path("id") id: Int
    ): List<RecipeStepDTO>

    @GET("recipes/{id}")
    suspend fun getRecipe(
        @Path("id")id: Int
    ) : RecipeDTO

    @GET("measure_types/")
    suspend fun getMeasureTypes() : List<MeasureTypeDTO>

    @Multipart
    @POST("upload_image/")
    suspend fun uploadImage(@Part file: MultipartBody.Part): String

    @POST("recipes/")
    suspend fun uploadRecipe(@Body recipe: RecipePostDTO)

    @POST("products/")
    suspend fun uploadProduct(@Body list: List<ProductPostDTO>)

    @POST("/products/realproduct")
    suspend fun uploadRealProduct(@Body requestBody: RealProductPostDTO)

    @GET("products/realproduct/product")
    suspend fun getProductByBarcode(@Header("barcode") barcode: String): Response<ProductDTO>



}