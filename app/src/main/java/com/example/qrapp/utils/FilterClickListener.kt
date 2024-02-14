package com.example.qrapp.utils

import com.example.qrapp.data.model.Filter

interface FilterClickListener {
    fun onClick(filter: Filter)
}