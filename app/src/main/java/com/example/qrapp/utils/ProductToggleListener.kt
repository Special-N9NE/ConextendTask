package com.example.qrapp.utils

import com.example.qrapp.data.model.Filter
import com.example.qrapp.data.model.Product

interface ProductToggleListener {
    fun onToggle(item : Product)
}