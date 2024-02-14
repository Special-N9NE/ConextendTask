package com.example.qrapp.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.qrapp.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    var items = listOf<Product>()
}