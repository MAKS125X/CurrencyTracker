package com.example.currencytracker.ui.settings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.currencytracker.db.SettingCurrency
import com.example.currencytracker.repository.CurrencyRepository
import com.example.currencytracker.repository.Resource
import com.example.currencytracker.repository.SelectedCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    val repository: CurrencyRepository
) : ViewModel() {

    var currencies = repository.getSettings().asLiveData()

    val changeableCurrency = MutableLiveData<SettingCurrency>()
    //val changeableCurrency: LiveData<SettingCurrency> = _changeableCurrency

    suspend fun updateSelectionByItem(settingCurrency: SettingCurrency){
        viewModelScope.launch{
            settingCurrency.isSelected = !settingCurrency.isSelected
            repository.updateSettings(settingCurrency)
        }
    }

    fun setCurrency(settingCurrency: SettingCurrency){
        changeableCurrency.value = settingCurrency
        Log.d("Settings", "Setted1: $settingCurrency")
        Log.d("Settings", "Setted2: ${changeableCurrency.value}")
    }

    suspend fun updateLevelByItem(newLevel: Double){
        viewModelScope.launch{
            val newCurrency = changeableCurrency.value
            newCurrency?.notificationLevel = newLevel
            if (newCurrency != null) {
                repository.updateSettings(newCurrency)
            }
        }
    }


}