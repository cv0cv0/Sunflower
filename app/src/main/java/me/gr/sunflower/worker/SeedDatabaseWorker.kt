package me.gr.sunflower.worker

import android.util.Log
import androidx.work.Worker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import me.gr.sunflower.data.AppDatabase
import me.gr.sunflower.data.Plant
import me.gr.sunflower.util.PLANT_DATA_FILENAME

class SeedDatabaseWorker : Worker() {
    private val tag = SeedDatabaseWorker::class.java.simpleName

    override fun doWork(): Result {
        val plantType = object : TypeToken<List<Plant>>() {}.type
        var jsonReader: JsonReader? = null

        return try {
            val inputStream = applicationContext.assets.open(PLANT_DATA_FILENAME)
            jsonReader = JsonReader(inputStream.reader())
            val plantList: List<Plant> = Gson().fromJson(jsonReader, plantType)
            val database = AppDatabase.getInstance(applicationContext)
            database.plantDao().insertAll(plantList)
            Worker.Result.SUCCESS
        } catch (e: Exception) {
            Log.e(tag, "Error seeding database", e)
            Worker.Result.FAILURE
        } finally {
            jsonReader?.close()
        }
    }
}