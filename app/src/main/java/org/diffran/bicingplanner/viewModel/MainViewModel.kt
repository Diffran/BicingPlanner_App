package org.diffran.bicingplanner.viewModel

import android.app.Application
import android.content.res.AssetManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.diffran.bicingplanner.data.AppRepository
import org.maplibre.android.style.sources.GeoJsonOptions
import org.maplibre.android.style.sources.GeoJsonSource
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Thread.State


class MainViewModel(private val repository :AppRepository, application: Application) : AndroidViewModel(application) {
    val context = application.applicationContext
    lateinit var dataBicing : String

    init{
        getBicingPred()
    }

    private fun getBicingPred(){
        viewModelScope.launch {
            try{
                dataBicing = repository.getBicingPred()
            }catch(e : IOException){
                //TODO: ...
            }
        }
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

    //aixo rep el nom del arxiu guardat i retorna el string del json
    fun loadGeoJsonFromAssets(fileName: String): String {
        val assetManager: AssetManager = context.assets
        val inputStream = assetManager.open(fileName)
        val reader = InputStreamReader(inputStream)
        return reader.readText()
    }

    //DEPENDENCY INJECTION
    class AppViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AndroidViewModel::class.java)) {
                return MainViewModel(repository, application) as T
            }
            throw IllegalArgumentException("Error en el viewModel Factory")
        }
    }

}