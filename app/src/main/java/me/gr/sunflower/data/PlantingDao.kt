package me.gr.sunflower.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PlantingDao {
    @Query("select * from planting")
    fun getPlantings(): LiveData<List<Planting>>

    @Query("select * from planting where id=:plantingId")
    fun getPlanting(plantingId: Long): LiveData<Planting>

    @Query("select * from planting where plant_id=:plantId")
    fun getPlantingForPlant(plantId: String): LiveData<Planting>

    @Transaction
    @Query("select * from plant")
    fun getPlantAndPlantingsList(): LiveData<List<PlantAndPlantings>>

    @Insert
    fun insertPlanting(planting: Planting): Long

    @Delete
    fun deletePlanting(planting: Planting)
}