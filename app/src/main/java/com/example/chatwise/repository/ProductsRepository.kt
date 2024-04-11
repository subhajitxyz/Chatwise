package com.example.chatwise.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chatwise.api.ProductsAPI
import com.example.chatwise.models.ProductsModel

class ProductsRepository(private val productsAPI: ProductsAPI) {
    private val productsLivedata = MutableLiveData<ProductsModel>()
    val products : LiveData<ProductsModel>
        get()= productsLivedata
    suspend fun getProducts(){
        val result =productsAPI.getProducts()
        if(result?.body()!=null){
            productsLivedata.postValue(result.body())
        }
    }
}