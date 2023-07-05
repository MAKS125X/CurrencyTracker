package com.example.currencytracker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencytracker.api.RetrofitInstance
import com.example.currencytracker.databinding.ActivityMainBinding
import com.example.currencytracker.models.toCurrencyModelList
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
            val list = RetrofitInstance.apiService.getMyData()
            Log.d("Aboba", list.toString())
            currencyAdapter = FavouriteCurrencyAdapter(list.toCurrencyModelList())
            withContext(Dispatchers.Main) {

                binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)


                binding.recyclerView.adapter = currencyAdapter

                currencyAdapter.notifyDataSetChanged()
            }
        }


    }
}