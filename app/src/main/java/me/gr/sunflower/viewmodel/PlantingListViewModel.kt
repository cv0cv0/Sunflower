package me.gr.sunflower.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import me.gr.sunflower.data.PlantAndPlantings
import me.gr.sunflower.data.PlantingRepository

class PlantingListViewModel internal constructor(plantingRepository: PlantingRepository) : ViewModel() {
    val plantings = plantingRepository.getPlantings()
    val plantAndPlantingList: LiveData<List<PlantAndPlantings>> = Transformations
            .map(plantingRepository.getPlantAndPlantingsList()) { plantAndPlantingsList ->
                plantAndPlantingsList.filter { it.plantings.isNotEmpty() }
            }
}