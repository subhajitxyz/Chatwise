package com.example.chatwise.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatwise.models.ProductsModel
import com.example.chatwise.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ProductsRepository):ViewModel() {
    init{
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProducts()
        }
    }
    val products : LiveData<ProductsModel>
        get() = repository.products
}