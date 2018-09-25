package me.gr.sunflower.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import java.util.Calendar.DAY_OF_YEAR

@Entity(tableName = "plant")
data class Plant(
        @PrimaryKey @ColumnInfo(name = "id") val plantId: String,
        val name: String,
        val description: String,
        val growZoneNumber: Int,
        val wateringInterval: Int = 7,
        val imageUrl: String = ""
) {
    fun shouldBeWatered(lastWateringDate: Calendar) = Calendar.getInstance() > lastWateringDate.apply { add(DAY_OF_YEAR, wateringInterval) }

    override fun toString(): String = name
}