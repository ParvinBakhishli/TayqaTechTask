package com.example.retrofitfinal.presentation

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.BundleCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.retrofitfinal.R
import com.example.retrofitfinal.databinding.BottomSheetFilterBinding
import com.example.retrofitfinal.presentation.adapters.FilterAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FilterBottomSheetFragment : BottomSheetDialogFragment(R.layout.bottom_sheet_filter) {

    private val viewModel by activityViewModels<MainViewModel>()

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

        val type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("type", FilterType::class.java)
        } else {
            arguments?.get("type")
        } as? FilterType ?: return

        setupObservers(type)
        setupUi()
    }

    private fun setupObservers(type: FilterType) {
        lifecycleScope.launch {
            when (type) {
                FilterType.Country -> {
                    val countries: List<FilterAdapter.FilterItem> = viewModel.fetchCountryNames()
                    adapter.submitList(countries)
                }
                FilterType.City -> {
                    val cities: List<FilterAdapter.FilterItem> = viewModel.fetchCityNames()
                    adapter.submitList(cities)

                }
            }
        }
    }

    private fun setupUi() {
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        const val TAG = "FilterBottomSheet"

        fun newInstance(type: FilterType) = FilterBottomSheetFragment().apply {
            arguments = bundleOf("type" to type)
        }
    }

}


enum class FilterType {
    Country,
    City;
}
