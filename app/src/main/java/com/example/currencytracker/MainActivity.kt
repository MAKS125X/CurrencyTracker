package com.example.currencytracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencytracker.databinding.ActivityMainBinding
import com.example.currencytracker.models.toCurrencyModelList
import com.example.currencytracker.repository.ResponseRepository
import com.example.currencytracker.ui.FavouriteCurrencyAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var currencyAdapter: FavouriteCurrencyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            val list = ResponseRepository.getCurrencyList()
            currencyAdapter = FavouriteCurrencyAdapter(list.toCurrencyModelList())
            withContext(Dispatchers.Main) {

                binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)


                binding.recyclerView.adapter = currencyAdapter

                currencyAdapter.notifyDataSetChanged()
            }
        }


    }
}