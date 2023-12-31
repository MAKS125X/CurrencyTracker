package com.example.currencytracker.ui.selectedCurrencies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currencytracker.R
import com.example.currencytracker.databinding.ItemSelectedCurrencyBinding
import com.example.currencytracker.repository.SelectedCurrency
import com.example.currencytracker.repository.format
import kotlin.math.absoluteValue

class SelectedCurrencyAdapter :
    ListAdapter<SelectedCurrency, SelectedCurrencyAdapter.SelectedCurrencyViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedCurrencyViewHolder =
        SelectedCurrencyViewHolder(
            ItemSelectedCurrencyBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: SelectedCurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = currentList.size

    class SelectedCurrencyViewHolder(_binding: ItemSelectedCurrencyBinding) :
        RecyclerView.ViewHolder(_binding.root) {

        var binding = ItemSelectedCurrencyBinding.bind(itemView)

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

        fun bind(model: SelectedCurrency) {
            with(binding) {

                imageView.setImageResource(getResourceId(model.charCode.lowercase()))
                currencyName.text = model.name
                date.text = model.date.subSequence(0, 10)
                nominalWithCode.text = "${model.nominal} ${model.charCode}"
                currentCost.text = model.value.toString()
                costChangeValue.text =
                    "${model.getSign()}${model.getDifference().absoluteValue.format(3)} / ${model.getSign()}${
                        model.getPercentageChange().format(1)
                    }%"

                if (model.value - model.previous > 0) {
                    costChangeMark.setImageResource(R.drawable.arrow_up)
                    costChangeValue.setTextColor(
                        ContextCompat.getColor(
                            binding.costChangeValue.context,
                            R.color.green
                        )
                    )
                } else {
                    costChangeMark.setImageResource(R.drawable.arrow_down)
                    costChangeValue.setTextColor(
                        ContextCompat.getColor(
                            binding.costChangeValue.context,
                            R.color.red
                        )
                    )
                }

                costChangeMark.setImageResource(
                    if (model.value - model.previous > 0)
                        R.drawable.arrow_up else R.drawable.arrow_down
                )
            }
        }
    }

    companion object{
        private val DiffCallback = object : DiffUtil.ItemCallback<SelectedCurrency>() {
            override fun areItemsTheSame(
                oldItem: SelectedCurrency,
                newItem: SelectedCurrency
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: SelectedCurrency,
                newItem: SelectedCurrency
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}