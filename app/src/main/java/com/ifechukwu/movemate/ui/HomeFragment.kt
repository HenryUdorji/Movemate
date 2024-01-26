package com.ifechukwu.movemate.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.ifechukwu.movemate.R
import com.ifechukwu.movemate.data.Vehicle
import com.ifechukwu.movemate.databinding.FragmentHomeBinding
import com.ifechukwu.movemate.ui.adapter.AvailableVehiclesAdapter


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val adapter = AvailableVehiclesAdapter(Vehicle.availableVehicles())
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.adapter = adapter
    }
}