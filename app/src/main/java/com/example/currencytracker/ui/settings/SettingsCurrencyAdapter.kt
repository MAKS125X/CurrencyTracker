package com.example.currencytracker.ui.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currencytracker.R
import com.example.currencytracker.databinding.ItemSettingCurrencyBinding
import com.example.currencytracker.db.SettingCurrency

class SettingsCurrencyAdapter(private val onClick: (item: SettingCurrency) -> Unit, private val onLongClick: (item: SettingCurrency) -> Unit) :
    ListAdapter<SettingCurrency, SettingsCurrencyAdapter.CurrencyViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder =
        CurrencyViewHolder(
            ItemSettingCurrencyBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onClick(current)
            notifyItemChanged(position)
        }
        holder.itemView.setOnLongClickListener {
            onLongClick(current)
            notifyItemChanged(position)
            true
        }
        holder.bind(current)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    class CurrencyViewHolder(_binding: ItemSettingCurrencyBinding) :
        RecyclerView.ViewHolder(_binding.root) {

        var binding = ItemSettingCurrencyBinding.bind(itemView)

        private fun getResourceId(name: String): Int {
            return when (name) {
                "aed" -> R.drawable.ic_aed
                "amd" -> R.drawable.ic_amd
                "aud" -> R.drawable.ic_aud
                "azn" -> R.drawable.ic_azn
                "bgn" -> R.drawable.ic_bgn
                "brl" -> R.drawable.ic_brl
                "byn" -> R.drawable.ic_byn
                "cad" -> R.drawable.ic_cad
                "chf" -> R.drawable.ic_chf
                "cny" -> R.drawable.ic_cny
                "czk" -> R.drawable.ic_czk
                "dkk" -> R.drawable.ic_dkk
                "egp" -> R.drawable.ic_egp
                "eur" -> R.drawable.ic_eur
                "gbp" -> R.drawable.ic_gbp
                "gel" -> R.drawable.ic_gel
                "hkd" -> R.drawable.ic_hkd
                "huf" -> R.drawable.ic_huf
                "idr" -> R.drawable.ic_idr
                "inr" -> R.drawable.ic_inr
                "jpy" -> R.drawable.ic_jpy
                "kgs" -> R.drawable.ic_kgs
                "krw" -> R.drawable.ic_krw
                "kzt" -> R.drawable.ic_kzt
                "mdl" -> R.drawable.ic_mdl
                "nok" -> R.drawable.ic_nok
                "nzd" -> R.drawable.ic_nzd
                "pln" -> R.drawable.ic_pln
                "qar" -> R.drawable.ic_qar
                "ron" -> R.drawable.ic_ron
                "rsd" -> R.drawable.ic_rsd
                "rub" -> R.drawable.ic_rub
                "sek" -> R.drawable.ic_sek
                "sgd" -> R.drawable.ic_sgd
                "thb" -> R.drawable.ic_thb
                "tjs" -> R.drawable.ic_tjs
                "tmt" -> R.drawable.ic_tmt
                "try" -> R.drawable.ic_try
                "uah" -> R.drawable.ic_uah
                "usd" -> R.drawable.ic_usd
                "uzs" -> R.drawable.ic_uzs
                "vnd" -> R.drawable.ic_vnd
                "xdr" -> R.drawable.ic_xdr
                "zar" -> R.drawable.ic_zar
                else -> R.drawable.ic_empty
            }
        }

        fun bind(model: SettingCurrency) {
            with(binding) {
                imageView.setImageResource(getResourceId(model.charCode.lowercase()))
                if (model.name == "") {
                    currencyName.text = "model.name"
                } else {
                    currencyName.text = model.name
                }
                charCodeTextView.text = model.charCode
                isSelecredImageView.setImageResource(if (model.isSelected) R.drawable.ic_selected else R.drawable.ic_unselected)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<SettingCurrency>() {
            override fun areItemsTheSame(
                oldItem: SettingCurrency,
                newItem: SettingCurrency
            ): Boolean {
                return oldItem.name == newItem.name && oldItem.isSelected == newItem.isSelected
            }

            override fun areContentsTheSame(
                oldItem: SettingCurrency,
                newItem: SettingCurrency
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}