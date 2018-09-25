package me.gr.sunflower.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import me.gr.sunflower.data.Plant
import me.gr.sunflower.data.PlantRepository
import me.gr.sunflower.data.PlantingRepository

class PlantDetailViewModel(
        plantRepository: PlantRepository,
        private val plantingRepository: PlantingRepository,
        private val plantId: String
) : ViewModel() {
    val isPlanted: LiveData<Boolean>
    val plant: LiveData<Plant>

    init {
        val plantingForPlant = plantingRepository.getPlantingForPlant(plantId)
        isPlanted = Transformations.map(plantingForPlant) { it != null }
        plant = plantRepository.getPlant(plantId)
    }

    fun addPlantToGarden() {
        plantingRepository.createPlanting(plantId)
    }
}