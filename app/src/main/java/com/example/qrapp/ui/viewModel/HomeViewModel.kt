package com.example.qrapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrapp.R
import com.example.qrapp.data.model.Filter
import com.example.qrapp.data.model.FilterType
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


    var isDatabaseEmpty = true
    var isFilterOpen = false
    var currentFilter = FilterType.ALL


    val filterItems = listOf(
        Filter("All", true, FilterType.ALL),
        Filter("On", false, FilterType.ON),
        Filter("Off", false, FilterType.OFF),
    )
    private val names = listOf(
        "Watch", "Car", "Phone", "Laptop"
    )
    private val images = listOf(
        R.drawable.i1,
        R.drawable.i2,
        R.drawable.i3,
        R.drawable.i4,
    )

    init {
        getProducts()
    }

    private fun setItems(): List<Product> {

        val result = mutableListOf<Product>()
        for (i in 0..5) {

            val random = Random.nextInt(0, 4)
            result.add(
                Product(
                    i.toLong(),
                    "156165465464",
                    names[random],
                    images[random],
                    i % 2 == 0
                )
            )
        }
        return result
    }

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getProducts(object : RepoCallback<List<Product>> {
                override fun onSuccessful(response: List<Product>) {

                    isDatabaseEmpty = response.isEmpty()

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
        val product = Product(null, data, names[random], images[random])

        viewModelScope.launch(Dispatchers.IO) {
            repo.insertProduct(product, object : RepoCallback<Long> {
                override fun onSuccessful(response: Long) {
                    product.id = response
                    items.add(product)
                    ldItems.postValue(items)
                }

                override fun onError(error: String) {
                    ldError.postValue(Event(error))
                }

            })
        }
    }

    fun filter(filter: FilterType, reload: Boolean = true) {
        currentFilter = filter


        val result = when (filter) {
            FilterType.ALL -> items
            FilterType.ON -> items.filter { it.isChecked }
            FilterType.OFF -> items.filter { !it.isChecked }
        }

        if (reload)
            ldItems.postValue(result)
    }

    fun productToggle(item: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.toggleProduct(item, object : RepoCallback<Unit> {
                override fun onSuccessful(response: Unit) {

                    items.forEach {
                        if (item.id == it.id) {
                            it.isChecked = item.isChecked
                        }
                    }

                    filter(currentFilter, currentFilter != FilterType.ALL)
                }

                override fun onError(error: String) {
                    ldError.postValue(Event(error))
                }
            })
        }
    }
}