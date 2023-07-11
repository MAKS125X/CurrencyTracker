package com.example.currencytracker.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencytracker.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    lateinit var binding: FragmentSettingsBinding
    private val viewModel: SettingsViewModel by activityViewModels()
    private lateinit var settingsAdapter: SettingsCurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsAdapter = SettingsCurrencyAdapter({
            lifecycleScope.launch {
                viewModel.updateSelectionByItem(it)
            }
        }
        ) {
            val bottomSheet = SettingsBottomSheetFragment()
            viewModel.setCurrency(it)
//            viewModel.changeableCurrency.value = it
            Log.d("Settings", viewModel.changeableCurrency.value.toString())
            bottomSheet.show(parentFragmentManager, SettingsBottomSheetFragment.TAG)
        }

        binding.recyclerView.apply {
            adapter = settingsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.currencies.observe(viewLifecycleOwner) {
            it.let {
                settingsAdapter.submitList(it)
            }
        }
    }

    companion object {
        const val TAG = "SettingsFragment"
    }
}