package com.example.qrapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.qrapp.databinding.FragmentHomeBinding
import com.example.qrapp.ui.adapter.ProductsAdapter
import com.example.qrapp.ui.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var b: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentHomeBinding.inflate(layoutInflater)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.items.let {
            if (it.isEmpty()) {
                b.gEmpty.visibility = View.VISIBLE
                b.rvProducts.visibility = View.GONE
            } else {
                b.gEmpty.visibility = View.GONE
                b.rvProducts.visibility = View.VISIBLE

                b.rvProducts.adapter = ProductsAdapter(it)
            }
        }

        setClicks()
    }

    private fun setClicks() {
        b.ivScan.setOnClickListener {
            //TODO handle click
        }
    }
}