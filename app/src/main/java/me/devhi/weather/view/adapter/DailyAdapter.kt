package me.devhi.weather.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.devhi.weather.view.DailyForecastUiState
import me.devhi.weather.R
import me.devhi.weather.databinding.ItemDailyForecastBinding

class DailyAdapter : ListAdapter<DailyForecastUiState, DailyAdapter.VH>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = DataBindingUtil.inflate<ItemDailyForecastBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_daily_forecast,
            parent,
            false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    class VH(private val binding: ItemDailyForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DailyForecastUiState) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<DailyForecastUiState>() {
            override fun areContentsTheSame(
                oldItem: DailyForecastUiState,
                newItem: DailyForecastUiState
            ) = oldItem == newItem

            override fun areItemsTheSame(
                oldItem: DailyForecastUiState,
                newItem: DailyForecastUiState
            ) = oldItem == newItem
        }
    }
}