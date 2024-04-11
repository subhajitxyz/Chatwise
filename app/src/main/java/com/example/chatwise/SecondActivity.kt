package com.example.chatwise

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatwise.adapters.ParentAdapter
import com.example.chatwise.api.ProductsAPI
import com.example.chatwise.api.RetrofitHelper
import com.example.chatwise.databinding.ActivitySecondBinding
import com.example.chatwise.models.Product
import com.example.chatwise.models.ProductsModel
import com.example.chatwise.repository.ProductsRepository
import com.example.chatwise.viewmodels.MainViewModel
import com.example.chatwise.viewmodels.MainViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    lateinit var mainViewModel: MainViewModel

    private lateinit var recyclerView: RecyclerView
    private var parentList = ArrayList<ProductsModel>()
    private var list= ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val productsService = RetrofitHelper.getInstance().create(ProductsAPI::class.java)
        val repository = ProductsRepository(productsService)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))
            .get(MainViewModel::class.java)


        recyclerView = findViewById(R.id.parentRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ParentAdapter(parentList)
        recyclerView.adapter = adapter


        mainViewModel.products.observe(this, Observer {
            parentList.clear()
            parentList.add(it)
            adapter.notifyDataSetChanged()
          Log.d("SUBHA", it.products.toString())
        })


    }

}