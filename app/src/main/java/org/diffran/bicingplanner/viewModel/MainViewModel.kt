package org.diffran.bicingplanner.viewModel

import android.app.Application
import android.content.res.AssetManager
import androidx.lifecycle.AndroidViewModel
import org.maplibre.android.style.sources.GeoJsonOptions
import org.maplibre.android.style.sources.GeoJsonSource
import java.io.InputStreamReader


class MainViewModel(application: Application) : AndroidViewModel(application) {
    val context = application.applicationContext

    fun loadGeoJsonFromAssets(fileName: String): String {
        val assetManager: AssetManager = context.assets
        val inputStream = assetManager.open(fileName)
        val reader = InputStreamReader(inputStream)
        return reader.readText()
    }

    fun getGeoSource(geoJsonData : String):GeoJsonSource{
        return GeoJsonSource(
            "bicing_stations",
            geoJsonData,
            GeoJsonOptions()
                .withCluster(true)
                .withClusterMaxZoom(12)
                .withClusterRadius(50)
        )
    }
}