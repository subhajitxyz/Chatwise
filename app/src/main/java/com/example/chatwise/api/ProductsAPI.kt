package com.example.chatwise.api

import com.example.chatwise.models.ProductsModel
import retrofit2.Response
import retrofit2.http.GET

interface ProductsAPI {
    @GET("/products")
    suspend fun getProducts(): Response<ProductsModel>
}