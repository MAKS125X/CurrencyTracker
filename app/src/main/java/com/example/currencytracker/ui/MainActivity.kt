package com.example.currencytracker.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
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
                val settingsFragment =
                    supportFragmentManager.findFragmentById(R.id.navigation_host_fragment)
                if ((settingsFragment !is SettingsFragment)) {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.navigation_host_fragment,
                            SettingsFragment(),
                            SettingsFragment.TAG
                        )
                        .addToBackStack(null)
                        .commit()
                }
                true
            }

            R.id.aboutButton -> {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder
                    .setMessage(
                        "Трекер курса валют ЦБ РФ\n" +
                                "В меню настроек есть возможность установить порог, " +
                                "при достижении которого будет отправляться уведомление. " +
                                "Для этого воспользуйтесь долгим нажатием\n" +
                                "API для курсов ЦБ РФ: www.cbr-xml-daily.ru/"
                    )
                    .setTitle("Особенности программы")
                    .setPositiveButton("Закрыть") { dialog, _ ->
                        dialog.cancel()
                    }

                val dialog: AlertDialog = builder.create()
                dialog.show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}