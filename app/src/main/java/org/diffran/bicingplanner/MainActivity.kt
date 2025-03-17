package org.diffran.bicingplanner

import android.content.res.AssetManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import org.diffran.bicingplanner.screen.FirstScreen
import org.diffran.bicingplanner.ui.theme.BicingPlannerTheme
import org.diffran.bicingplanner.viewModel.MainViewModel

import android.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.maplibre.android.MapLibre
import org.maplibre.android.style.expressions.Expression
import org.maplibre.android.style.layers.CircleLayer
import org.maplibre.android.style.layers.PropertyFactory
import org.maplibre.android.style.layers.SymbolLayer
import org.maplibre.android.style.sources.GeoJsonOptions
import org.maplibre.android.style.sources.GeoJsonSource
import org.ramani.compose.MapLibre
import java.io.InputStreamReader
import java.net.URI


class MainActivity : ComponentActivity() {

//    companion object {
//        lateinit var DEFAULT_STYLE_URL: String
//    }
//    private val viewModel: MainViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        DEFAULT_STYLE_URL = getString(R.string.maplibre_style_url)
//
//        setContent {
//            BicingPlannerTheme {
//                FirstScreen(DEFAULT_STYLE_URL, viewModel)
//            }
//        }
//    }
private var currentStrokeColor = "LightGreen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This is usually done in the MapLibre composable, but in this case we need to initialize
        // the map earlier in order to define the sources and layers.
        MapLibre.getInstance(this)


        //TODO: PASAR AIXO AL VIEWMODEL
        // Define the source GeoJson.
        val geoJsonData = loadGeoJsonFromAssets("bicing_stations.geojson")
        val mySource = GeoJsonSource(
            "earthquakes",
            geoJsonData,
            GeoJsonOptions()
                .withCluster(true)
                .withClusterMaxZoom(13)
                .withClusterRadius(50),
        )

        // Define a layer made of all the data points from the "earthquakes" source
        // that have a "mag" property. Also set the color of the data points based on "mag".
        val unclustered = CircleLayer("unclustered", "earthquakes")
//        unclustered.setFilter(Expression.has("mag"))
        unclustered.setProperties(
            PropertyFactory.circleRadius(5F),
            PropertyFactory.circleColor(
                "Blue"
//                Expression.interpolate(
//                    Expression.exponential(1),
//                    Expression.get("mag"),
//                    Expression.stop(2.0, Expression.rgb(0, 255, 0)),
//                    Expression.stop(4.5, Expression.rgb(0, 0, 255)),
//                    Expression.stop(7.0, Expression.rgb(255, 0, 0)),
//                )
            ),
            PropertyFactory.circleStrokeColor("LightGreen"),
            PropertyFactory.circleStrokeWidth(5f),
        )

        // Maplibre will create cluster data points automatically. Those points can be identified
        // from their "point_count" property, which says how many points the cluster contains.
        // We extract that value here to use it for the cluster layers below.
        val pointCount = Expression.toNumber(Expression.get("point_count"))

        // Create a layer made of the cluster points that were created for the "earthquakes" source.
        // Note how we select the points that have "point_cloud > 1", which are clusters.
        val clusters = CircleLayer("cluster", "earthquakes")
        clusters.setFilter(Expression.gt(pointCount, 1))
        clusters.setProperties(
            PropertyFactory.circleColor(Color.BLACK),
            PropertyFactory.circleRadius(18F),
        )

        // Create another layer, this time to show the "point_count" on top of the cluster circles
        // defined in the "clusters" layer above.
        val numbers = SymbolLayer("count", "earthquakes")
        numbers.setFilter(Expression.gt(pointCount, 1))
        numbers.setProperties(
            PropertyFactory.textField(Expression.toString(pointCount)),
            PropertyFactory.textSize(12F),
            PropertyFactory.textColor(Color.WHITE),
            PropertyFactory.textIgnorePlacement(true),
            PropertyFactory.textAllowOverlap(true),
        )

        setContent {
            BicingPlannerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        MapLibre(//TODO: modificar el meu map perque rebi aixo
                            modifier = Modifier.weight(1f),
                            sources = listOf(mySource),
                            layers = listOf(unclustered, clusters, numbers),
                        )
                        Button(
                            onClick = {
                                // Canviar el color del stroke entre 3 colors diferents
                                currentStrokeColor = when (currentStrokeColor) {
                                    "grey" -> "red"
                                    "red" -> "blue"
                                    else -> "grey"
                                }
                                //changeStrokeColor(unclustered, currentStrokeColor)
                                changeCircleColor(unclustered, currentStrokeColor)
                            },
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text("Canviar Stroke Color")
                        }
                    }
                }
            }
        }
    }
    //AIXO JA VA EN EL COMPOSABLE, SEGONS QUAN CLIQUI EN EL BOTONS FLOTANTS
    private fun changeCircleColor(layer: CircleLayer, newColor: String) {
        layer.setProperties(
            PropertyFactory.circleColor(newColor) // Canviar el color del bord
        )
    }
    //ho he passat ja al view model
    private fun loadGeoJsonFromAssets(fileName: String): String {
        val assetManager: AssetManager = assets
        val inputStream = assetManager.open(fileName)
        val reader = InputStreamReader(inputStream)
        return reader.readText()
    }
}




