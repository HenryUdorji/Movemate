package com.ifechukwu.movemate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.ifechukwu.movemate.R
import com.ifechukwu.movemate.data.SearchItem
import com.ifechukwu.movemate.data.Shipment
import com.ifechukwu.movemate.data.Status
import com.ifechukwu.movemate.databinding.ItemSearchItemBinding
import com.ifechukwu.movemate.databinding.ItemShipmentHistoryBinding

class SearchItemAdapter(private var searchItems: List<SearchItem>) :
    RecyclerView.Adapter<SearchItemAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemSearchItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return searchItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val searchItem = searchItems[position]
            binding.tvName.text = searchItem.name
            binding.tvNumber.text = searchItem.number
            binding.tvLocationFirst.text = searchItem.location.first
            binding.tvLocationSecond.text = searchItem.location.second

            binding.root.startAnimation(AnimationUtils.loadAnimation(binding.root.context, R.anim.slide_in_child_bottom))
        }
    }

    fun filterItems(filteredItems: List<SearchItem>) {
        searchItems = filteredItems
        notifyDataSetChanged()
    }
}