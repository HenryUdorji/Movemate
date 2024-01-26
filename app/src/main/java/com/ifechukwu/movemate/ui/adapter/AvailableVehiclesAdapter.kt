package com.ifechukwu.movemate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifechukwu.movemate.data.Vehicle
import com.ifechukwu.movemate.databinding.ItemVehicleBinding

class AvailableVehiclesAdapter(private val availableVehicles: List<Vehicle>): RecyclerView.Adapter<AvailableVehiclesAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemVehicleBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVehicleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return availableVehicles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val vehicle = availableVehicles[position]
            binding.tvVehicleTitle.text = vehicle.name
            binding.tvVehicleSubtitle.text = vehicle.type
            binding.ivImage.setImageResource(vehicle.icon)
        }
    }
}