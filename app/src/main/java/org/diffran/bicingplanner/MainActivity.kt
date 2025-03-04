package org.diffran.bicingplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.diffran.bicingplanner.ui.theme.BicingPlannerTheme
import org.maplibre.android.MapLibre
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapView


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BicingPlannerTheme {
                MapLibre.getInstance(this)

                MapViewCompose()
            }
        }
    }
}

@Composable
fun MapViewCompose() {
    AndroidView(
        factory = { context ->
            MapView(context).apply {
                onCreate(null)
                getMapAsync { map ->
                    map.setStyle("https://demotiles.maplibre.org/style.json") {

                        // Configurar la cámara para acercarse y mostrar calles
                        map.cameraPosition = CameraPosition.Builder()
                            .target(LatLng(41.3784, 2.1915))
                            .zoom(12.0)
                            .build()
                    }
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

//@Composable
//fun MapViewCompose() {
//    AndroidView(
//        factory = { context ->
//            MapView(context).apply {
//                onCreate(null)
//                getMapAsync { map ->
//                    map.setStyle("https://demotiles.maplibre.org/style.json")
//                    map.cameraPosition = CameraPosition.Builder()
//                        .target(LatLng(41.3784, 2.1915))
//                        .zoom(12.0)
//                        .build()
//                }
//
//            }
//        },
//        modifier = Modifier.fillMaxSize()
//    )
//}

