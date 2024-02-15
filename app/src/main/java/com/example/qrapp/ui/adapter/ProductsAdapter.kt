package com.example.qrapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.qrapp.R
import com.example.qrapp.data.model.Product
import com.example.qrapp.databinding.ItemProductBinding
import com.example.qrapp.utils.ProductToggleListener

class ProductsAdapter(
    private var products: List<Product>,
    val listener: ProductToggleListener
) : RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val b: ItemProductBinding) : RecyclerView.ViewHolder(b.root) {

        fun setData(position: Int) {
            val item = products[position]
            val number = products.size - position


            b.model = item
            b.ivImage.setImageResource(item.image)
            b.tvNumber.text = "$number."

            b.sw.setOnClickListener {
                item.isChecked = !item.isChecked
                listener.onToggle(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemProductBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_product, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setData(position)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun updateList(list: List<Product>) {
        products = list.toMutableList()
        notifyDataSetChanged()
    }

}