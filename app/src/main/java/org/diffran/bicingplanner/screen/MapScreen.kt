package org.diffran.bicingplanner.screen

import android.graphics.Color
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import org.diffran.bicingplanner.viewModel.MainViewModel
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.Style
import org.maplibre.android.style.expressions.Expression
import org.maplibre.android.style.layers.CircleLayer
import org.maplibre.android.style.layers.PropertyFactory
import org.maplibre.android.style.layers.SymbolLayer
import org.ramani.compose.MapLibre

@Composable
fun MapScreen(mapStyle : String, searchType: String, viewModel: MainViewModel, timeRange :Int) {
    org.maplibre.android.MapLibre.getInstance(viewModel.context)

    val unclustered =createUnclusteredLayer()
    val styleBuilder = Style.Builder().fromUri(mapStyle)
    val myDataSource = viewModel.loadGeoJsonFromAssets( "bicing_stations.geojson")

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
            LaunchedEffect(searchType) {
                Toast.makeText(viewModel.context, "Canvia a $searchType", Toast.LENGTH_LONG).show()
            }
            key(searchType){
                MapLibre(
                    modifier = Modifier.fillMaxSize(),
                    styleBuilder = styleBuilder,
                    cameraPosition = cameraPosition.value,
                    sources = listOf(viewModel.getGeoSource(myDataSource)),
                    layers = listOf(unclustered)
                ){
                }
            }
        }
    }
}

fun changeCircleColor(layer: CircleLayer, newColor: String) {
    layer.setProperties(
        PropertyFactory.circleColor(newColor)
    )
}

private fun createUnclusteredLayer(): CircleLayer {
    return CircleLayer("unclustered", "bicing_stations").apply {
        setProperties(
            PropertyFactory.circleRadius(7F),
            PropertyFactory.circleColor("Blue"),
            PropertyFactory.circleStrokeColor(
                Expression.interpolate(
                    Expression.exponential(1),
                    Expression.get("color"),
                    Expression.stop(0, "darkred"),
                    Expression.stop(1, "orange"),
                    Expression.stop(2, "yellow"),
                    Expression.stop(3, "lightgreen"),
                )
            ),
            PropertyFactory.circleStrokeWidth(7f),
        )
    }
}



