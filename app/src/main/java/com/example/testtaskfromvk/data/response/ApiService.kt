package com.example.testtaskfromvk.data.response

import com.example.testtaskfromvk.data.productmodel.AllProducts
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): Response<AllProducts>

    @GET("products/search")
    suspend fun searchProducts(
        @Query("q") name: String
    ):Response<AllProducts>

    @GET("products/categories")
    suspend fun getCategories(): Response<List<String>>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category:String,
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): Response<AllProducts>
}