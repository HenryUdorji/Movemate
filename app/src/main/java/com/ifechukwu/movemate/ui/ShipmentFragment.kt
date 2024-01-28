package com.ifechukwu.movemate.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.ifechukwu.movemate.R
import com.ifechukwu.movemate.data.Shipment
import com.ifechukwu.movemate.data.Status
import com.ifechukwu.movemate.databinding.FragmentShipmentBinding
import com.ifechukwu.movemate.ui.adapter.ShipmentHistoryAdapter

class ShipmentFragment : Fragment() {
    private lateinit var binding: FragmentShipmentBinding
    private lateinit var adapter: ShipmentHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentShipmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Status.entries.map { item ->
            val newTab = binding.tblStatus.newTab()
            newTab.text = item.value
            newTab.orCreateBadge.apply {
                isVisible = true
                number =
                    if (item.value == Status.All.value) Shipment.shipmentHistory().size
                    else Shipment.shipmentHistory()
                        .filter { shipment -> shipment.status == item }.size
                backgroundColor =
                    if (item.value == Status.All.value) resources.getColor(R.color.accent_color)
                    else resources.getColor(R.color.tab_unselected_color)
                badgeTextColor = resources.getColor(R.color.white)
                maxCharacterCount = 3
                horizontalOffset = 8
            }
            binding.tblStatus.addTab(newTab)
        }

        val layoutManager = LinearLayoutManager(requireContext())
        adapter = ShipmentHistoryAdapter(Shipment.shipmentHistory())
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.adapter = adapter

        binding.tblStatus.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.badge?.backgroundColor = resources.getColor(R.color.accent_color)
                val shipments = if (tab?.text.toString() == Status.All.value) {
                    Shipment.shipmentHistory()
                } else {
                    Shipment.shipmentHistory()
                        .filter { shipment -> shipment.status.value == tab?.text.toString() }
                        .toList()
                }
                adapter.updateShipments(shipments)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.badge?.backgroundColor = resources.getColor(R.color.tab_unselected_color)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_shipmentFragment_to_homeFragment)
        }
    }
}