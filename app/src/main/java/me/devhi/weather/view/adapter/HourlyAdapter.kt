package me.devhi.weather.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.devhi.weather.view.HourlyForecastUiState
import me.devhi.weather.R
import me.devhi.weather.databinding.ItemHourlyForecastBinding

class HourlyAdapter : ListAdapter<HourlyForecastUiState, HourlyAdapter.VH>(diffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = DataBindingUtil.inflate<ItemHourlyForecastBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_hourly_forecast,
            parent,
            false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    class VH(private val binding: ItemHourlyForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HourlyForecastUiState) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<HourlyForecastUiState>() {
            override fun areContentsTheSame(
                oldItem: HourlyForecastUiState,
                newItem: HourlyForecastUiState
            ) = oldItem == newItem

            override fun areItemsTheSame(
                oldItem: HourlyForecastUiState,
                newItem: HourlyForecastUiState
            ) = oldItem == newItem
        }
    }
}