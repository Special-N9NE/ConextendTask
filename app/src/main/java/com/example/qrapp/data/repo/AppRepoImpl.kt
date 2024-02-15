package com.example.qrapp.data.repo

import com.example.qrapp.data.model.Product
import com.example.qrapp.data.source.AppDatabase
import com.example.qrapp.data.source.ProductEntity
import com.example.qrapp.utils.BaseRepoImpl
import com.example.qrapp.utils.RepoCallback
import javax.inject.Inject

class AppRepoImpl @Inject constructor(private val db: AppDatabase) : AppRepo, BaseRepoImpl() {

    override suspend fun getProducts(callback: RepoCallback<List<Product>>) {

        runWithTryCatch(callback) {
            val data = db.getDao().getProducts()
            if (data.isEmpty()) {
                emptyList()
            } else {
                data.map {
                    Product(it.id, it.sn, it.name, it.image, it.isChecked)
                }
            }
        }
    }

    override suspend fun insertProduct(product: Product, callback: RepoCallback<Long>) {

        val data = ProductEntity(
            product.id, product.sn, product.name, product.image, product.isChecked
        )

        runWithTryCatch(callback) {
            db.getDao().insertProduct(data)
        }
    }

    override suspend fun toggleProduct(product: Product, callback: RepoCallback<Unit>) {
        runWithTryCatch(callback) {
            db.getDao().toggleProduct(product.id!!, product.isChecked)
        }
    }

}
