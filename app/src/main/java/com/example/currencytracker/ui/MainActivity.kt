package com.example.currencytracker.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.currencytracker.R
import com.example.currencytracker.databinding.ActivityMainBinding
import com.example.currencytracker.ui.selectedCurrencies.SelectedCurrenciesFragment
import com.example.currencytracker.ui.settings.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.navigation_host_fragment,
                SelectedCurrenciesFragment(),
                SelectedCurrenciesFragment.TAG
            )
            .addToBackStack(null)
            .commit()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settingsButton -> {
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.navigation_host_fragment,
                        SettingsFragment(),
                        SettingsFragment.TAG
                    )
                    .addToBackStack(null)
                    .commit()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}