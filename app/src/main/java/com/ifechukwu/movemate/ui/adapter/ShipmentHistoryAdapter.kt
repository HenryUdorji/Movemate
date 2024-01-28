package com.ifechukwu.movemate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.ifechukwu.movemate.R
import com.ifechukwu.movemate.data.Shipment
import com.ifechukwu.movemate.data.Status
import com.ifechukwu.movemate.databinding.ItemShipmentHistoryBinding

class ShipmentHistoryAdapter(private var shipments: List<Shipment>) :
    RecyclerView.Adapter<ShipmentHistoryAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemShipmentHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemShipmentHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return shipments.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val shipment = shipments[position]
            binding.tvVehicleTitle.text = shipment.title
            binding.tvVehicleSubtitle.text = shipment.subtitle
            binding.tvAmount.text = shipment.amount
            binding.tvDate.text = shipment.date

            when (shipment.status) {
                Status.All -> {
                    //DO NOTHING
                }

                Status.Completed -> {
                    binding.tvStatus.apply {
                        text = shipment.status.value
                        setTextColor(context.resources.getColor(R.color.color_blue))
                        setCompoundDrawablesRelativeWithIntrinsicBounds(
                            R.drawable.ic_check, 0, 0, 0
                        )
                    }
                }

                Status.InProgress -> {
                    binding.tvStatus.apply {
                        text = "In-Progress"
                        setTextColor(context.resources.getColor(R.color.color_green))
                        setCompoundDrawablesRelativeWithIntrinsicBounds(
                            R.drawable.ic_progress_cycle, 0, 0, 0
                        )
                    }
                }

                Status.Pending -> {
                    binding.tvStatus.apply {
                        text = "Pending"
                        setTextColor(context.resources.getColor(R.color.accent_color))
                        setCompoundDrawablesRelativeWithIntrinsicBounds(
                            R.drawable.ic_shipment, 0, 0, 0
                        )
                    }
                }

                Status.Cancelled -> {
                    binding.tvStatus.apply {
                        text = shipment.status.value
                        setTextColor(context.resources.getColor(R.color.color_red))
                        setCompoundDrawablesRelativeWithIntrinsicBounds(
                            R.drawable.ic_close, 0, 0, 0
                        )
                    }
                }
            }

            binding.root.startAnimation(AnimationUtils.loadAnimation(binding.root.context, R.anim.slide_in_child_bottom))
        }
    }

    fun updateShipments(shipments: List<Shipment>) {
        this.shipments = shipments
        notifyDataSetChanged()
    }
}