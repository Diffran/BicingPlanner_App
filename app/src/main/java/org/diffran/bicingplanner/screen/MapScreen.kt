package org.diffran.bicingplanner.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import org.diffran.bicingplanner.R
import org.diffran.bicingplanner.viewModel.MainViewModel
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.Style
import org.ramani.compose.CircleWithItem
import org.ramani.compose.MapLibre

@Composable
fun MapScreen(mapStyle : String, searchType: String, viewModel: MainViewModel) {
    val bicingstatioData = viewModel.loadDataFromAssets()
    val stations = bicingstatioData.data.stations
    val styleBuilder = Style.Builder().fromUri(mapStyle)

    val json = viewModel.generateJson(stations)
    viewModel.saveJsonToFile(json,"bicing_data_bike_false.json")
    //valors camera
    val cameraPosition = rememberSaveable {
        mutableStateOf(
            org.ramani.compose.CameraPosition(
                target = LatLng(41.387016, 2.170047),
                zoom = 13.0,
            )
        )
    }

    Box{
        Surface (
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            MapLibre(
                modifier = Modifier.fillMaxSize(),
                styleBuilder = styleBuilder,
                cameraPosition = cameraPosition.value,
            ){
                stations.forEach {
                    station ->
                    pinStations(searchType,0, LatLng(station.lat, station.lon))
                }
            }
        }
    }
}


@Composable
fun pinStations(searchType : String, stationPrediction : Int, latLng: LatLng){

    val imageId = when (searchType) {
        "EL" -> R.drawable.bicing_logo_electrica
        "MEC" -> R.drawable.bicing_logo_mecanica
        "DOCK" -> R.drawable.bicing_logo_docks
        else -> R.drawable.bicing_logo_electrica
    }

    key(searchType){//pot tenir mes d'una key(searchType, stationPrediction, latLng)
        CircleWithItem(
            center = latLng,
            radius =7f,
            itemSize = 0.05f,
            imageId = imageId,
            isDraggable = false,
            color = "White",
            borderWidth = 7f,
            borderColor = "Orange")//LigthGreen, DarkRed, Orange, yellow,
    }
}

