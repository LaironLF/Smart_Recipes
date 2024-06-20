package com.laironlf.smartRecipes.data.api

import com.laironlf.smartRecipes.data.repository.BarcodeRepository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BarcodeApiService: BarcodeRepository {
    @GET("saerch_baza_ean.php")
    suspend fun getData(@Query("k") query: String): Response<String>


}