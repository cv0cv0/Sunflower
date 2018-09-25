package me.gr.sunflower.data

import androidx.room.Embedded
import androidx.room.Relation

class PlantAndPlantings {
    @Embedded
    var plant: Plant? = null

    @Relation(parentColumn = "id", entityColumn = "plant_id")
    var plantings: List<Planting> = arrayListOf()
}