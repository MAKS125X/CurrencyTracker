package com.example.currencytracker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.currencytracker.R
import com.example.currencytracker.databinding.ItemCurrencyBinding
import com.example.currencytracker.models.CurrencyModel
import com.example.currencytracker.models.format
import kotlin.math.absoluteValue

class FavouriteCurrencyAdapter(private val dataSet: MutableList<CurrencyModel>) :
    RecyclerView.Adapter<FavouriteCurrencyAdapter.CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder =
        CurrencyViewHolder(
            ItemCurrencyBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )


    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    class CurrencyViewHolder(_binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(_binding.root) {

        var binding = ItemCurrencyBinding.bind(itemView)

        fun bind(model: CurrencyModel) {
            with(binding) {
                imageView.setImageResource(R.drawable.ic_usd)
                currencyName.text = model.Name
                date.text = model.Date.subSequence(0, 10)
                nominalWithCode.text = "${model.Nominal} ${model.CharCode}"
                currentCost.text = model.Value.toString()
                costChangeValue.text =
                    "${model.getSign()}${model.getDifference().absoluteValue.format(3)} / " +
                            "${model.getSign()}${model.getPercentageChange().format(1)}%"

                if (model.Value - model.Previous > 0) {
                    costChangeMark.setImageResource(R.drawable.arrow_up)
                    currentCost.setTextColor(
                        ContextCompat.getColor(
                            binding.currentCost.context,
                            R.color.green
                        )
                    )
                    costChangeValue.setTextColor(
                        ContextCompat.getColor(
                            binding.costChangeValue.context,
                            R.color.green
                        )
                    )
                } else {
                    costChangeMark.setImageResource(R.drawable.arrow_down)
                    currentCost.setTextColor(
                        ContextCompat.getColor(
                            binding.currentCost.context,
                            R.color.red
                        )
                    )
                    costChangeValue.setTextColor(
                        ContextCompat.getColor(
                            binding.costChangeValue.context,
                            R.color.red
                        )
                    )
                }

                costChangeMark.setImageResource(
                    if (model.Value - model.Previous > 0)
                        R.drawable.arrow_up else R.drawable.arrow_down
                )
            }
        }
    }
}