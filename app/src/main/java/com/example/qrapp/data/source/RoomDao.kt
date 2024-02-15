package com.example.qrapp.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: ProductEntity): Long

    @Query("SELECT * FROM ProductEntity")
    fun getProducts(): List<ProductEntity>


    @Query("UPDATE ProductEntity SET isChecked  = :enable WHERE id = :id")
    fun toggleProduct(id: Long, enable: Boolean)
}