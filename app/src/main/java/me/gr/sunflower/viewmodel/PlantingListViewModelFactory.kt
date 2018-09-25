package me.gr.sunflower.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.gr.sunflower.data.PlantingRepository

class PlantingListViewModelFactory(private val repository: PlantingRepository):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlantingListViewModel(repository) as T
    }
}