package com.example.currencytracker.ui.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.currencytracker.db.SettingCurrency
import com.example.currencytracker.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    val repository: CurrencyRepository
) : ViewModel() {

    var currencies = repository.getSettings().asLiveData()

    val changeableCurrency = MutableLiveData<SettingCurrency>()

    suspend fun updateSelectionByItem(settingCurrency: SettingCurrency) {
        viewModelScope.launch {
            settingCurrency.isSelected = !settingCurrency.isSelected
            repository.updateSettings(settingCurrency)
        }
    }

    fun setCurrency(settingCurrency: SettingCurrency) {
        changeableCurrency.value = settingCurrency
    }

    suspend fun updateLevelByItem(newLevel: Double) {
        viewModelScope.launch {
            val newCurrency = changeableCurrency.value
            newCurrency?.notificationLevel = newLevel
            if (newCurrency != null) {
                repository.updateSettings(newCurrency)
            }
        }
    }
}