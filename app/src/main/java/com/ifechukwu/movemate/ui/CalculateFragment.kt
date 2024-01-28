package com.ifechukwu.movemate.ui

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.chip.ChipGroup.OnCheckedStateChangeListener
import com.ifechukwu.movemate.R
import com.ifechukwu.movemate.databinding.FragmentCalculateBinding

class CalculateFragment : Fragment() {
    private lateinit var binding: FragmentCalculateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCalculateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categories =
            listOf("Documents", "Glass", "Liquid", "Food", "Electronic", "Product", "Others")

        categories.map { category ->
            binding.cgCategories.addChip(requireContext(), category)
        }

        //Animate the EditText Fields
        binding.linearSender.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_child_bottom))
        binding.linearReceiver.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_child_bottom))
        binding.linearWeight.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_child_bottom))
        binding.tvPackagingLabel.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_child_bottom))
        binding.tvPackagingSubtitle.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_child_bottom))
        binding.linearBox.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_child_bottom))
        binding.btnCalculate.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_child_bottom))

        binding.btnCalculate.setOnClickListener {
            findNavController().navigate(R.id.action_calculateFragment_to_statusFragment)
        }

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_calculateFragment_to_homeFragment)
        }
    }
}

/**
 * This extension allows for easy creation and adding
 * of chips to the chip group
 */
fun ChipGroup.addChip(context: Context, label: String) {
    Chip(context).apply {
        id = View.generateViewId()
        text = label
        isClickable = true
        isFocusable = true
        isCheckable = true
        checkedIcon = null
        chipCornerRadius = 10f
        chipStrokeWidth = 3f
        chipStrokeColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.chip_checked_color))
        chipBackgroundColor = resources.getColorStateList(R.color.chip_color_state, null)
        setTextColor(resources.getColorStateList(R.color.chip_text_color_state, null))

        addView(this)
        startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_right))
    }
}