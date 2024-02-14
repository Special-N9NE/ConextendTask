package com.example.qrapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.qrapp.ScanActivity
import com.example.qrapp.data.model.Product
import com.example.qrapp.databinding.FragmentHomeBinding
import com.example.qrapp.ui.adapter.ProductsAdapter
import com.example.qrapp.ui.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var b: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private var startScannerForResult: ActivityResultLauncher<Intent>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentHomeBinding.inflate(layoutInflater)
        return b.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startScannerForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->

                if (result.resultCode == Activity.RESULT_OK) {
                    val data = result.data?.getStringExtra("scan") ?: ""

                    viewModel.addProduct(data)
                } else if (result.resultCode == Activity.RESULT_CANCELED) {
                    val data = result.data?.getStringExtra("scan")
                    data?.let {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                    }
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClicks()

        setObservers()

    }


    private fun setObservers() {
        viewModel.ldItems.observe(viewLifecycleOwner) {
            checkForItems(it)
        }
    }

    private fun checkForItems(items: List<Product>) {
        items.let {
            if (it.isEmpty()) {
                b.gEmpty.visibility = View.VISIBLE
                b.rvProducts.visibility = View.GONE
            } else {
                b.gEmpty.visibility = View.GONE
                b.rvProducts.visibility = View.VISIBLE

                b.rvProducts.adapter = ProductsAdapter(it)
            }
        }
    }


    private fun setClicks() {
        b.ivScan.setOnClickListener {
            val intent = Intent(requireActivity(), ScanActivity::class.java)
            startScannerForResult?.launch(intent)
        }
    }
}