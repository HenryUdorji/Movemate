package com.ifechukwu.movemate.ui

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifechukwu.movemate.R
import com.ifechukwu.movemate.data.SearchItem
import com.ifechukwu.movemate.data.Vehicle
import com.ifechukwu.movemate.databinding.FragmentSearchBinding
import com.ifechukwu.movemate.ui.adapter.AvailableVehiclesAdapter
import com.ifechukwu.movemate.ui.adapter.SearchItemAdapter

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: SearchItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.slide_right)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        val layoutManager = LinearLayoutManager(requireContext())
        adapter = SearchItemAdapter(SearchItem.searchItems())
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.adapter = adapter

        binding.edtSearch.addTextChangedListener { editable ->
            val query = editable?.toString()
            if (query?.isEmpty() == true) {
                adapter.filterItems(SearchItem.searchItems())
            } else {
                val filteredItems = SearchItem.searchItems()
                    .filter { searchItem -> searchItem.number.lowercase().contains(query?.lowercase().toString()) }
                    .toList()

                adapter.filterItems(filteredItems)
            }
        }
    }
}