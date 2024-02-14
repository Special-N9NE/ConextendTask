package com.example.qrapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrapp.R
import com.example.qrapp.data.model.Product
import com.example.qrapp.data.repo.AppRepoImpl
import com.example.qrapp.utils.Event
import com.example.qrapp.utils.RepoCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: AppRepoImpl
) : ViewModel() {

    val ldError = MutableLiveData<Event<String>>()
    val ldItems = MutableLiveData<List<Product>>()
    private var items = mutableListOf<Product>()

    init {
        getProducts()
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

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getProducts(object : RepoCallback<List<Product>> {
                override fun onSuccessful(response: List<Product>) {
                    ldItems.postValue(response)
                    items.clear()
                    items = response.toMutableList()
                }

                override fun onError(error: String) {
                    ldError.postValue(Event(error))
                }

            })
        }
    }

    fun addProduct(data: String) {
        val random = Random.nextInt(0, 4)
        val product = Product(data, names[random], images[random])

        viewModelScope.launch(Dispatchers.IO) {
            repo.insertProduct(product, object : RepoCallback<Unit> {
                override fun onSuccessful(response: Unit) {
                    items.add(product)
                    ldItems.postValue(items)
                }

                override fun onError(error: String) {
                    ldError.postValue(Event(error))
                }

            })
        }
    }

}