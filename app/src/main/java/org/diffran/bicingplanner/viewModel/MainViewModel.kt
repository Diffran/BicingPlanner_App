package org.diffran.bicingplanner.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import org.diffran.bicingplanner.model.BicingStation
import org.diffran.bicingplanner.model.BicingStationData
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.random.Random


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext

    fun loadDataFromAssets(): BicingStationData {
        val jsonString = context.assets.open("bicing_stations.json").bufferedReader().use { it.readText() }
        val gson = Gson()
        val type = object : TypeToken<BicingStationData>() {}.type

        return gson.fromJson(jsonString, type)
    }


    // Funció per generar percentatges aleatoris per cada categoria (0, 1, 2, +3)
    fun generatePercentages(): Map<String, Int> {
        val categories = listOf("0", "1", "2", "+3")
        val percentages = mutableMapOf<String, Int>()
        var total = 0

        // Generem percentatges aleatoris per cada categoria
        categories.dropLast(1).forEach { category ->
            val remainingPercentage = 100 - total
            val randomPercentage = Random.nextInt(0, remainingPercentage + 1)
            percentages[category] = randomPercentage
            total += randomPercentage
        }

        // Assegurem-nos que la suma sigui exactament 100 afegint la resta al "+3"
        percentages["+3"] = 100 - total

        return percentages
    }

    // Generem el JSON
    fun generateJson(stations: List<BicingStation>): JsonObject {
        val gson = Gson()
        val jsonObject = JsonObject()

        stations.forEach { station ->
            val timeSlots = mutableMapOf<String, TimeSlotData>()

            // Generem percentatges per cada interval de temps
            val timeIntervals = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12","13","14","15","16","17","18","19","20","21","21","22","23","0")
            timeIntervals.forEach {  timeInterval ->
                timeSlots[timeInterval] = generatePercentages()
            }

            // Creem l'objecte JSON per l'estació
            val stationJson = JsonObject().apply {
                timeSlots.forEach { (timeInterval, timeSlotData) ->
                    add(timeInterval, gson.toJsonTree(timeSlotData))
                }
            }

            jsonObject.add(station.station_id.toString(), stationJson)
        }

        return jsonObject
    }

    // Funció per guardar el JSON en un fitxer
    fun saveJsonToFile( json: JsonObject, filename: String) {
        try {
            // Crear el fitxer a la ruta dels fitxers de l'aplicació
            val file = File(context.filesDir, filename)
            val fileOutputStream = FileOutputStream(file)

            // Convertim el JSON a String
            val jsonString = Gson().toJson(json)

            // Escriure el contingut en el fitxer
            fileOutputStream.write(jsonString.toByteArray())
            fileOutputStream.close()

            println("Fitxer guardat a: ${file.absolutePath}")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}


data class BicingStationData(
    val stations: List<BicingStation>
)

data class BicingStation(
    val station_id: Int
)

// Dades per cada interval de temps (1-2, 2-3, etc.)
data class TimeSlotData(
    val dock: Map<String, Int>,  // Percentatges de cada tipus de dock
    val ebike: Map<String, Int>, // Percentatges de cada tipus de ebike
    val bike: Map<String, Int>   // Percentatges de cada tipus de bike
)