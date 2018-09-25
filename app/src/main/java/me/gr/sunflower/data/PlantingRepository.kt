package me.gr.sunflower.data

import me.gr.sunflower.util.runOnIOThread

class PlantingRepository private constructor(private val plantingDao: PlantingDao) {

    fun createPlanting(plantId: String) = runOnIOThread {
        val planting = Planting(plantId)
        plantingDao.insertPlanting(planting)
    }


    fun removePlanting(planting: Planting) = runOnIOThread {
        plantingDao.deletePlanting(planting)
    }


    fun getPlantingForPlant(plantId: String) = plantingDao.getPlantingForPlant(plantId)

    fun getPlantings() = plantingDao.getPlantings()

    fun getPlantAndPlantingsList() = plantingDao.getPlantAndPlantingsList()

    companion object {
        @Volatile
        private var instance: PlantingRepository? = null

        fun getInstance(plantingDao: PlantingDao) = instance ?: synchronized(this) {
            instance ?: PlantingRepository(plantingDao).also { instance = it }
        }
    }
}