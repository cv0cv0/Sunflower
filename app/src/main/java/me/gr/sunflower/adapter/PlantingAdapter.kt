package me.gr.sunflower.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.gr.sunflower.data.PlantAndPlantings
import me.gr.sunflower.databinding.ItemPlantingBinding
import me.gr.sunflower.viewmodel.PlantAndPlantingsViewModel

class PlantingAdapter : ListAdapter<PlantAndPlantings, PlantingAdapter.ViewHolder>(PlantingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPlantingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let {
            with(holder) {
                itemView.tag = it
                bind(it)
            }
        }
    }

    class ViewHolder(private val binding: ItemPlantingBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(plantAndPlantings: PlantAndPlantings) {
            with(binding) {
                viewModel = PlantAndPlantingsViewModel(itemView.context, plantAndPlantings)
                executePendingBindings()
            }
        }
    }
}

class PlantingDiffCallback : DiffUtil.ItemCallback<PlantAndPlantings>() {
    override fun areItemsTheSame(oldItem: PlantAndPlantings, newItem: PlantAndPlantings): Boolean {
        return oldItem.plant?.plantId == newItem.plant?.plantId
    }

    override fun areContentsTheSame(oldItem: PlantAndPlantings, newItem: PlantAndPlantings): Boolean {
        return oldItem.plant == newItem.plant
    }
}