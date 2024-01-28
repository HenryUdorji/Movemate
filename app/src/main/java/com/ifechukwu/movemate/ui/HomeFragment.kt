package com.ifechukwu.movemate.ui

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
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

        binding.edtSearch.setOnClickListener {
            val extras = FragmentNavigatorExtras(binding.edtSearch to "search_edt")
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment, null, null, extras)
        }

        //Load Animations
        binding.tvTracking.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_child_bottom))
        binding.cvShipmentInfo.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_child_bottom))
        binding.tvAvailableVehicles.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_child_bottom))

        //This animation affects the Shared Element transition of the Search EditText
        //binding.appBar.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_top))
    }
}