package org.diffran.bicingplanner.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.diffran.bicingplanner.model.BicingStationData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext

    fun loadDataFromAssets(): BicingStationData {
        val jsonString = context.assets.open("bicing_stations.json").bufferedReader().use { it.readText() }
        val gson = Gson()
        val type = object : TypeToken<BicingStationData>() {}.type

        return gson.fromJson(jsonString, type)
    }
}
