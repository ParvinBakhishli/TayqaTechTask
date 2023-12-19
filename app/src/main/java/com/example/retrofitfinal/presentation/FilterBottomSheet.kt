package com.example.retrofitfinal.presentation

import android.os.Bundle
import android.view.View
import com.example.retrofitfinal.R
import com.example.retrofitfinal.databinding.BottomSheetFilterBinding
import com.example.retrofitfinal.presentation.adapters.FilterAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterBottomSheet : BottomSheetDialogFragment(R.layout.bottom_sheet_filter) {

    private var _binding: BottomSheetFilterBinding? = null
    private val binding get() = requireNotNull(_binding) {
        "Binding is null sad:("
    }

    private val adapter by lazy {
        FilterAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = BottomSheetFilterBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() {
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
