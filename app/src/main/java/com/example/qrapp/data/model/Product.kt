package com.example.qrapp.data.model

import androidx.annotation.DrawableRes

data class Product(
    val sn: String,
    val name: String,
    @DrawableRes val image: Int,
    var isChecked : Boolean = false
)
