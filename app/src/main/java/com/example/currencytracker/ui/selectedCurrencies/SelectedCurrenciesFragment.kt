package com.example.currencytracker.ui.selectedCurrencies

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermission()
        }

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
                is Resource.Loading -> {}
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

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (!isGranted) {
            val alertDialog: AlertDialog? = this.let {
                val builder = AlertDialog.Builder(context)
                builder.apply {
                    setMessage("Приложению необходим доступ к Вашим контактам для проверки работы служб")
                    setPositiveButton("Предоставить разрешение") { _, _ ->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        intent.data = Uri.parse("package:" + context.packageName)
                        startActivity(intent)
                    }
                    setNegativeButton("Не сейчас") { _, _ ->
                    }
                }
                builder.create()
            }
            alertDialog?.show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestPermission() {
        when {
            context?.let {
                ContextCompat.checkSelfPermission(
                    it, android.Manifest.permission.POST_NOTIFICATIONS
                )
            } == PackageManager.PERMISSION_GRANTED -> {
            }

            shouldShowRequestPermissionRationale(
                android.Manifest.permission.POST_NOTIFICATIONS
            ) -> {
                requestPermissionLauncher.launch(
                    android.Manifest.permission.POST_NOTIFICATIONS
                )
            }

            else -> {
                requestPermissionLauncher.launch(
                    android.Manifest.permission.POST_NOTIFICATIONS
                )
            }
        }
    }

    companion object {
        const val TAG = "FavouriteCurrenciesFragment"
    }
}