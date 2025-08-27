package com.laironlf.smartRecipes.data.api


import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface EanOnlineApiService {
    @FormUrlEncoded
    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Referer: https://ean-online.ru/"
    )
    @POST("match.php")
    suspend fun getProduct(
        @Field("barcode") barcode: String
    ): String
}