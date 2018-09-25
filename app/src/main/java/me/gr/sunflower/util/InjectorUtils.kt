package me.gr.sunflower.util

import android.content.Context
import me.gr.sunflower.data.AppDatabase
import me.gr.sunflower.data.PlantRepository
import me.gr.sunflower.data.PlantingRepository
import me.gr.sunflower.viewmodel.PlantDetailViewModelFactory
import me.gr.sunflower.viewmodel.PlantListViewModelFactory
import me.gr.sunflower.viewmodel.PlantingListViewModelFactory

object InjectorUtils {

    private fun getPlantRepository(context: Context): PlantRepository {
        return PlantRepository.getInstance(AppDatabase.getInstance(context).plantDao())
    }

    private fun getPlatingRepository(context: Context): PlantingRepository {
        return PlantingRepository.getInstance(AppDatabase.getInstance(context).plantingDao())
    }

    fun providePlantingListViewModelFactory(context: Context): PlantingListViewModelFactory {
        val repository = getPlatingRepository(context)
        return PlantingListViewModelFactory(repository)
    }

    fun providePlantListViewModelFactory(context: Context): PlantListViewModelFactory {
        val repository = getPlantRepository(context)
        return PlantListViewModelFactory(repository)
    }

    fun providePlantDetailViewModelFactory(context: Context, plantId: String): PlantDetailViewModelFactory {
        return PlantDetailViewModelFactory(getPlantRepository(context), getPlatingRepository(context), plantId)
    }
}