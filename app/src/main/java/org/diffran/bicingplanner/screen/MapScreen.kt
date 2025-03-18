package org.diffran.bicingplanner.screen

import android.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
//    var currentStrokeColor = "LightGreen"
    org.maplibre.android.MapLibre.getInstance(viewModel.context)

    val unclustered =createUnclusteredLayer()
    //val pointCount = Expression.toNumber(Expression.get("point_count"))
    val clustered = createClusterLayer()
    val numbers = createNumbersLayer()
    val styleBuilder = Style.Builder().fromUri(mapStyle)
    val myDataSource = viewModel.loadGeoJsonFromAssets( "bicing_stations.geojson")

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
                sources = listOf(viewModel.getGeoSource(myDataSource)),
                layers = listOf(unclustered,clustered,numbers)
            ){

//                Button(
//                    onClick = {
//                        // Canviar el color del stroke entre 3 colors diferents
//                        val currentStrokeColor = when (currentStrokeColor) {
//                            "grey" -> "red"
//                            "red" -> "blue"
//                            else -> "grey"
//                        }
//                        //changeStrokeColor(unclustered, currentStrokeColor)
//                        changeCircleColor(unclustered, currentStrokeColor)
//                    },
//                    modifier = Modifier.padding(16.dp)
//                ) {
//                    Text("Canviar Stroke Color")
//                }
            }
        }
    }
}

private fun changeCircleColor(layer: CircleLayer, newColor: String) {
    layer.setProperties(
        PropertyFactory.circleColor(newColor)
    )
}

private fun createUnclusteredLayer(): CircleLayer {
    return CircleLayer("unclustered", "bicing_stations").apply {
        setProperties(
            PropertyFactory.circleRadius(7F),
            PropertyFactory.circleColor("Blue"),
           // PropertyFactory.circleStrokeColor("lightgreen"),
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

private fun createClusterLayer(): CircleLayer {
    val pointCount = Expression.toNumber(Expression.get("point_count"))
    return CircleLayer("cluster", "bicing_stations").apply {
        setFilter(Expression.gt(pointCount, 1))
        setProperties(
            PropertyFactory.circleColor(Color.BLACK),
            PropertyFactory.circleRadius(50F),
        )
    }
}

private fun createNumbersLayer(): SymbolLayer {
    val pointCount = Expression.toNumber(Expression.get("point_count"))
    return SymbolLayer("count", "bicing_stations").apply {
        setFilter(Expression.gt(pointCount, 1))
        setProperties(
            PropertyFactory.textField(Expression.toString(pointCount)),
            PropertyFactory.textSize(12F),
            PropertyFactory.textColor(Color.WHITE),
            PropertyFactory.textIgnorePlacement(true),
            PropertyFactory.textAllowOverlap(true),
        )
    }
}



