package me.gr.sunflower.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.gr.sunflower.data.PlantRepository
import me.gr.sunflower.data.PlantingRepository

class PlantDetailViewModelFactory(
        private val plantRepository: PlantRepository,
        private val plantingRepository: PlantingRepository,
        private val plantId: String
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlantDetailViewModel(plantRepository, plantingRepository, plantId) as T
    }
}