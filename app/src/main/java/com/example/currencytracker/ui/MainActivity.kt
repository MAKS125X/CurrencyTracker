package com.example.currencytracker.ui

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
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
                val messageText = SpannableString(
                    "Чтобы установить порог, " +
                            "при достижении которого будет отправляться уведомление, " +
                            "воспользуйтесь долгим нажатием по карточке (в меню строек). " +
                            "Для этого воспользуйтесь долгим нажатием\n\n" +
                            "API для курсов ЦБ РФ: www.cbr-xml-daily.ru/"
                )
                messageText.setSpan(
                    URLSpan("http://www.cbr-xml-daily.ru/"),
                    messageText.indexOf("www.cbr-xml-daily.ru"),
                    messageText.indexOf("www.cbr-xml-daily.ru") + "www.cbr-xml-daily.ru".length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder
                    .setMessage(messageText)
                    .setTitle("Особенности программы")
                    .setPositiveButton("Закрыть") { dialog, _ ->
                        dialog.cancel()
                    }

                val dialog: AlertDialog = builder.create()
                dialog.show()

                dialog.findViewById<TextView>(android.R.id.message)?.movementMethod =
                    LinkMovementMethod.getInstance()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}