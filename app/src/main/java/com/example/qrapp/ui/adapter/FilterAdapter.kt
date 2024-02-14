package com.example.qrapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.qrapp.R
import com.example.qrapp.data.model.Filter
import com.example.qrapp.databinding.ItemFilterBinding
import com.example.qrapp.utils.FilterClickListener

class FilterAdapter(
    private var list: List<Filter>,
    val listener: FilterClickListener
) : RecyclerView.Adapter<FilterAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val b: ItemFilterBinding) : RecyclerView.ViewHolder(b.root) {

        fun setData(position: Int) {
            val item = list[position]
            b.model = item

            b.iv.visibility = if (item.isChecked) View.VISIBLE else View.GONE

            b.root.setOnClickListener {
                listener.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemFilterBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_filter, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setData(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}