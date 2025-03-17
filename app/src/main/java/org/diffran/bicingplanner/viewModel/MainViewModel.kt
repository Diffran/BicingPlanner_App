package org.diffran.bicingplanner.viewModel

import android.app.Application
import android.content.res.AssetManager
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.diffran.bicingplanner.model.BicingState
import org.diffran.bicingplanner.model.BicingStationData
import org.diffran.bicingplanner.model.BicingStationState
import java.io.InputStreamReader


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext

    private fun loadGeoJsonFromAssets(fileName: String): String {
        val assetManager: AssetManager = context.assets
        val inputStream = assetManager.open(fileName)
        val reader = InputStreamReader(inputStream)
        return reader.readText()
    }

    //aixo te pinta de que sobra... :(
    fun loadDataFromAssets(): BicingStationData {
        val jsonString = context.assets.open("bicing_stations.json").bufferedReader().use { it.readText() }
        val gson = Gson()
        val type = object : TypeToken<BicingStationData>() {}.type

        return gson.fromJson(jsonString, type)
    }

    fun loadBicingStatesFromAssets(): List<BicingState> {
        val gson = Gson()
        val type = object : TypeToken<BicingState>() {}.type
        val fileNames = listOf(
            "eBike_fake.json",
            "bike_fake.json",
            "docks_fake.json"
        )
        val combinedBicingStates = mutableListOf<BicingState>()

        fileNames.forEach { fileName ->
            val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            val bicingStates: BicingState = gson.fromJson(jsonString, type)
            combinedBicingStates.add(bicingStates)
        }

        return combinedBicingStates
    }

    fun readTimeSlot(bicingState: BicingStationState, timeSlot: String): String {
        val timeSlotData = bicingState.time_slots.find { it.time_slot == timeSlot }
        return timeSlotData?.percentagesHighest ?: "0"
    }

    //TODO: dades falses de moment
    fun searchForBicingType(searchType:String): BicingState{
        val bicingPercentageList = loadBicingStatesFromAssets()
        return when (searchType) {
            "EL" -> bicingPercentageList[0]
            "MEC" -> bicingPercentageList[1]
            "DOCK" -> bicingPercentageList[2]
            else -> bicingPercentageList[0]
        }
    }
}


//TODO: resar per no haver de fer un json de dades mes :(

//// Generar un percentatge aleatori entre 0, 1, 2 o 3
//private fun generateRandomPercentage(): String {
//    val percentages = listOf("0", "1", "2", "3")
//    return percentages[Random.nextInt(percentages.size)]
//}
//
//// Convertir les dades generades a JSON
//fun convertToJson(stations: List<BicingStationState>): String {
//    val gson = Gson()
//    return gson.toJson(stations)
//}
//
//fun saveJsonFile(stations: List<BicingStation>){
//    val json = convertToJson(generateStationData(stations))
//    val file = File(context.filesDir,"bicing_fake.json")
//    file.writeText(json)
//}
// Genera les dades aleatòries per les estacions passades
//fun generateStationData(stations: List<BicingStation>): List<BicingStationState> {
//    return stations.map { station ->
//        // Generem TimeSlots per cada estació (de 0 a 23)
//        val timeSlots = (0..23).map { hour ->
//            // Generem un percentatge aleatori entre 0, 1, 2 o 3
//            val randomPercentage = generateRandomPercentage()
//            TimeSlotData(
//                time_slot = hour.toString(),
//                percentagesHighest = randomPercentage
//            )
//        }
//        BicingStationState(
//            station_id = station.station_id,
//            time_slots = timeSlots
//        )
//    }
//}