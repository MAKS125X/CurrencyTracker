package com.example.currencytracker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.currencytracker.R
import com.example.currencytracker.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.navigation_host_fragment,
                FavouriteCurrenciesFragment(),
                FavouriteCurrenciesFragment.TAG
            )
            .addToBackStack(null)
            .commit()


    }
}