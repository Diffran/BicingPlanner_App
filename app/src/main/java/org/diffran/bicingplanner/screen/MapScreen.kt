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
import org.diffran.bicingplanner.viewModel.MainViewModel
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.Style
import org.maplibre.android.style.layers.CircleLayer
import org.maplibre.android.style.layers.PropertyFactory
import org.ramani.compose.Circle
import org.ramani.compose.MapLibre

@Composable
fun MapScreen(mapStyle : String, searchType: String, viewModel: MainViewModel, timeRange :Int) {
    val stations = viewModel.loadDataFromAssets().data.stations
    val styleBuilder = Style.Builder().fromUri(mapStyle)
    val bicingByTypeData = viewModel.searchForBicingType(searchType)

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
                for (i in stations.indices) {

                    val station = stations[i]
                    val percentageData = bicingByTypeData.stations.find { it.station_id == station.station_id }
                    val stationPrediction = percentageData?.let { viewModel.readTimeSlot(it, timeRange.toString()) } ?: "8"

                    pinStations(searchType, stationPrediction, LatLng(station.lat, station.lon))
                }
            }
        }
    }
}


@Composable
fun pinStations(searchType : String, stationPrediction : String, latLng: LatLng){

    val imageColor = when (searchType) {
        "EL" -> "Blue"
        "MEC" -> "DarkRed"
        "DOCK" -> "DarkGrey"
        else -> "Blue"
    }

    val borderColor = when (stationPrediction) {
        "0" -> "Red"
        "1" -> "Orange"
        "2" -> "Yellow"
        "3" -> "LightGreen"
        else -> "White"
    }

    key(searchType,stationPrediction){
        Circle(
            center = latLng,
            radius =7f,
            isDraggable = false,
            color = imageColor,
            borderWidth = 7f,
            borderColor = borderColor)
    }
}

private fun changeCircleColor(layer: CircleLayer, newColor: String) {
    layer.setProperties(
        PropertyFactory.circleColor(newColor)
    )
}


