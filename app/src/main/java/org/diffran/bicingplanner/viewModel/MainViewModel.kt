package org.diffran.bicingplanner.viewModel

import android.app.Application
import android.content.res.AssetManager
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
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


class MainViewModel(private val repository :AppRepository) : ViewModel() {
    var dataBicing : String = ""

    init{
        getBicingPred()
    }

    fun getBicingPred(){
        viewModelScope.launch {
            try{
                val responseBody = repository.getBicingPred()

                dataBicing = responseBody.toString()
                Log.d("AQUIIIIIII ->   !!!!!!! -> MainViewModel", "Esta es el dataBicing: $dataBicing")
            }catch(e : IOException){
                Log.e("API_Error", "Error al obtener los datos: ${e.message}")
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

//    //aixo rep el nom del arxiu guardat i retorna el string del json
//    fun loadGeoJsonFromAssets(fileName: String): String {
//        val assetManager: AssetManager = context.assets
//        val inputStream = assetManager.open(fileName)
//        val reader = InputStreamReader(inputStream)
//        return reader.readText()
//    }

    //DEPENDENCY INJECTION
    class AppViewModelFactory(private val repository: AppRepository, ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(repository) as T
            }
            throw IllegalArgumentException("Error en el viewModel Factory")
        }
    }

}