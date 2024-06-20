package com.laironlf.smartRecipes.data.api

import com.laironlf.smartRecipes.data.dto.productBarcodeResponce.ProductBarcodeResponce
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface GS1RusApiService {

    @FormUrlEncoded
    @POST("GEPIR40/getItem")
    suspend fun getItem(
        @Field("keyValue") keyValue: String?,
        @Field("task") task: String? = task_getItemByGTIN,
        @Field("lng") lng: String? = LANGUAGE
    ): ProductBarcodeResponce

    companion object{
        const val task_getItemByGTIN = "getItemByGTIN"
        const val LANGUAGE = "ru"
    }
}