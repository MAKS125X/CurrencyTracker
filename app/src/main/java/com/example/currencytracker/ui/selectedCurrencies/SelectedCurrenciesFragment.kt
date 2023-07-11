package com.example.currencytracker.ui.selectedCurrencies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencytracker.databinding.FragmentSelectedCurrenciesBinding
import com.example.currencytracker.repository.Resource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectedCurrenciesFragment : Fragment() {

    private lateinit var binding: FragmentSelectedCurrenciesBinding
    private val viewModel: SelectedCurrencyViewModel by viewModels()
    private lateinit var currenciesAdapter: SelectedCurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectedCurrenciesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currenciesAdapter = SelectedCurrencyAdapter()

        binding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.refreshCurrencies()
                swipeRefreshLayout.isRefreshing = false
            }
            recyclerView.apply {
                adapter = currenciesAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        initViewModelObserver()
    }

    private fun initViewModelObserver() {
        viewModel.currencies.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> Log.d("RESOURCE", "LOADING")
                is Resource.SuccessFromApi -> currenciesAdapter.submitList(
                        result.data?.toMutableList() ?: mutableListOf()
                    )

                is Resource.SuccessFromDao -> currenciesAdapter.submitList(
                    result.data?.toMutableList() ?: mutableListOf()
                )

                is Resource.Error -> {
                    if (result.error is java.net.UnknownHostException &&
                        result.data?.toMutableList().isNullOrEmpty()
                    ) {
                        val snackbar = Snackbar.make(
                            binding.constraintLayout,
                            "Нет возможности проверить актуальность данных.\nПроверьте соединение с интернетом",
                            Snackbar.LENGTH_LONG
                        )
                        snackbar.setAction("Обновить") {
                            viewModel.refreshCurrencies()
                        }
                        snackbar.show()
                    }
                    currenciesAdapter.submitList(
                        result.data?.toMutableList() ?: mutableListOf()
                    )
                }
            }
        }
        viewModel.refreshCurrencies()
    }

    companion object {
        const val TAG = "FavouriteCurrenciesFragment"
    }
}