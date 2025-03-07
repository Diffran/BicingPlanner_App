package org.diffran.bicingplanner.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.Style
import org.ramani.compose.MapLibre

@Composable
fun MapScreen(mapStyle : String) {
    val styleBuilder = Style.Builder().fromUri(mapStyle)
    //valors camera
    val cameraPosition = rememberSaveable {
        mutableStateOf(
            org.ramani.compose.CameraPosition(
                target = LatLng(41.3784, 2.1815),
                zoom = 11.0,
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

            }
        }
    }
}

@Composable
fun pinStations(searchType : Int, stationPrediction : Int){

}

