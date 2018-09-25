package me.gr.sunflower.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.gr.sunflower.data.Plant
import me.gr.sunflower.databinding.ItemPlantBinding
import me.gr.sunflower.ui.PlantListFragmentDirections

class PlantAdapter : ListAdapter<Plant, PlantAdapter.ViewHolder>(PlantDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPlantBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plant = getItem(position)
        with(holder) {
            itemView.tag = plant
            bind(createOnClickListener(plant.plantId), plant)
        }
    }

    private fun createOnClickListener(plantId: String) = View.OnClickListener {
        val direction = PlantListFragmentDirections.PlantListFragmentToPlantDetailFragment(plantId)
        it.findNavController().navigate(direction)
    }

    class ViewHolder(private val binding: ItemPlantBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Plant) {
            with(binding) {
                onClick = listener
                plant = item
                executePendingBindings()
            }
        }
    }
}

class PlantDiffCallback : DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }
}