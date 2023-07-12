package com.example.currencytracker.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.currencytracker.databinding.FragmentSettingsBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentSettingsBottomSheetBinding
    private val viewModel: SettingsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.changeableCurrency.value?.let {
            binding.levelEditText.setText(it.notificationLevel.toString())
        }

        binding.completeButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.updateLevelByItem(binding.levelEditText.text.toString().toDouble())
            }
            this.dismiss()
        }
    }

    companion object {
        const val TAG = "SettingsBottomSheet"
    }
}