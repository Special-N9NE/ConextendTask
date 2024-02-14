package com.example.qrapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qrapp.R
import com.example.qrapp.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    val ldItems = MutableLiveData<List<Product>>()
    private var items = mutableListOf<Product>()

    init {
        ldItems.postValue(items)
    }

    private val names = listOf(
        "Watch", "Car", "Phone", "Laptop"
    )
    private val images = listOf(
        R.drawable.i1,
        R.drawable.i2,
        R.drawable.i3,
        R.drawable.i4,
    )

    fun addProduct(data: String) {
        val random = Random.nextInt(0, 4)
        items.add(
            Product(data, names[random], images[random])
        )
        ldItems.postValue(items)
    }

}