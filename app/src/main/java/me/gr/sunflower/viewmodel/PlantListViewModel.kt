package me.gr.sunflower.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import me.gr.sunflower.data.Plant
import me.gr.sunflower.data.PlantRepository

class PlantListViewModel internal constructor(private val repository: PlantRepository) : ViewModel() {
    private val growZoneNumber = MutableLiveData<Int>()
    private val plantList = MediatorLiveData<List<Plant>>()

    companion object {
        private const val NO_GROW_ZONE = -1
    }

    init {
        growZoneNumber.value = NO_GROW_ZONE

        val livePlantList = Transformations.switchMap(growZoneNumber) {
            if (it == NO_GROW_ZONE) {
                repository.getPlants()
            } else {
                repository.getPlantsWithGrowZoneNumber(it)
            }
        }
        plantList.addSource(livePlantList, plantList::setValue)
    }

    fun getPlants() = plantList

    fun setGrowZoneNumber(num: Int) {
        growZoneNumber.value = num
    }

    fun clearGrowZoneNumber() {
        growZoneNumber.value = NO_GROW_ZONE
    }

    fun isFiltered() = growZoneNumber.value != NO_GROW_ZONE
}