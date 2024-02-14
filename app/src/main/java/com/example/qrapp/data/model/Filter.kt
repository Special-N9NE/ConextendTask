package com.example.qrapp.data.model

data class Filter(
    val title: String,
    var isChecked: Boolean,
    val filterType: FilterType
)

enum class FilterType {
    ALL,
    ON,
    OFF
}