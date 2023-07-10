package com.example.currencytracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencytracker.databinding.FragmentFavouriteCurrenciesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteCurrenciesFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteCurrenciesBinding
    private val viewModel: CurrencyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteCurrenciesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val restaurantAdapter = FavouriteCurrencyAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = restaurantAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }

            viewModel.currencies.observe(viewLifecycleOwner) { result ->
                restaurantAdapter.submitList(result.data?.toMutableList() ?: mutableListOf())
                restaurantAdapter.notifyDataSetChanged()
            }
        }
    }

    companion object{
        const val TAG = "FavouriteCurrenciesFragment"
    }

}