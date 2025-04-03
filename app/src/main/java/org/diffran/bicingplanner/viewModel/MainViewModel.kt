package org.diffran.bicingplanner.viewModel

import android.app.Application
import android.content.res.AssetManager
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.diffran.bicingplanner.data.AppRepository
import org.maplibre.android.style.sources.GeoJsonOptions
import org.maplibre.android.style.sources.GeoJsonSource
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Thread.State


class MainViewModel(private val repository :AppRepository) : ViewModel() {
//    //var dataBicing by mutableStateOf("")
//        private set
    private val _dataBicing = MutableLiveData<String>()
    val dataBicing: LiveData<String> get() = _dataBicing
    val errorMessage = mutableStateOf<String?>(null)
    var serverError = false

    init{
        getBicingPred("EL",8)
    }

    fun getBicingPred(type: String, hour: Int){
        Log.d("BICINGAPI","TYPUS:$type HORA: $hour")
        viewModelScope.launch {
            try{
                var result :String = repository.getBicingPred(type,hour)
                _dataBicing.value = result.replace("\n","")
                serverError = false

                Log.d("BICING API", "dataBicing correcta")
                Log.d("BICING API", "TAMANY STRING REBUT -> " + dataBicing.toString().length)
            }catch(e : IOException){
                errorMessage.value = "Error al obtener los datos: ${e.message}"
                Log.e("BICING API", "Error al obtener los datos: ${e.message}")
                serverError = true
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