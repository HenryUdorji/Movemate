package com.ifechukwu.movemate.ui

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.ifechukwu.movemate.R
import com.ifechukwu.movemate.databinding.FragmentStatusBinding


class StatusFragment : Fragment() {
   private lateinit var binding: FragmentStatusBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentStatusBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBackHome.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.ivParcel.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_child_bottom_30))

        ValueAnimator.ofInt(0, 1460).apply {
            duration = 2000 // 2 seconds
            addUpdateListener { animation ->
                binding.tvAmount.text = "$" + animation.animatedValue.toString() + " USD"
            }
            start()
        }
    }
}