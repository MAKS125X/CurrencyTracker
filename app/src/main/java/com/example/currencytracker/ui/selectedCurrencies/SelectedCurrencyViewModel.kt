package com.example.currencytracker.ui.selectedCurrencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencytracker.repository.CurrencyRepository
import com.example.currencytracker.repository.Resource
import com.example.currencytracker.repository.SelectedCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectedCurrencyViewModel @Inject constructor(
    val repository: CurrencyRepository
) : ViewModel() {

    private val _currencies = MutableLiveData<Resource<List<SelectedCurrency>>>()
    val currencies: LiveData<Resource<List<SelectedCurrency>>> = _currencies

    fun refreshCurrencies() {
        viewModelScope.launch {
            repository.getCurrenciesWithBoundResources().collect { result ->
                _currencies.value = result
            }
        }
    }
}