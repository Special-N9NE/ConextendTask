package com.example.qrapp.data.repo

import com.example.qrapp.data.model.Product
import com.example.qrapp.utils.RepoCallback

interface AppRepo {
    suspend fun getProducts(callback: RepoCallback<List<Product>>)
    suspend fun insertProduct(product: Product, callback: RepoCallback<Long>)

    suspend fun toggleProduct(product: Product, callback: RepoCallback<Unit>)
}