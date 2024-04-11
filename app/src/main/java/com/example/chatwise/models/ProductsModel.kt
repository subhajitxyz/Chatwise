package com.example.chatwise.models

data class ProductsModel(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)
