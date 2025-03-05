package org.diffran.bicingplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import org.diffran.bicingplanner.ui.component.Pin
import org.diffran.bicingplanner.ui.theme.BicingPlannerTheme
import org.maplibre.android.MapLibre
import org.maplibre.android.annotations.MarkerOptions
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapLibreMap
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.Style


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
    Column {
        Pin(painterResource(R.drawable.ic_quest_bicycle))

        AndroidView(
            factory = { context ->
                MapView(context).apply {
                    onCreate(null)
                    getMapAsync { map ->
                        map.setStyle("https://maps.geo.us-east-2.amazonaws.com/maps/v0/maps/OpenDataStyle/style-descriptor?key=v1.public.eyJqdGkiOiI1NjY5ZTU4My0yNWQwLTQ5MjctODhkMS03OGUxOTY4Y2RhMzgifR_7GLT66TNRXhZJ4KyJ-GK1TPYD9DaWuc5o6YyVmlikVwMaLvEs_iqkCIydspe_vjmgUVsIQstkGoInXV_nd5CcmqRMMa-_wb66SxDdbeRDvmmkpy2Ow_LX9GJDgL2bbiCws0wupJPFDwWCWFLwpK9ICmzGvNcrPbX5uczOQL0N8V9iUvziA52a1WWkZucIf6MUViFRf3XoFkyAT15Ll0NDypAzY63Bnj8_zS8bOaCvJaQqcXM9lrbTusy8Ftq8cEbbK5aMFapXRjug7qcrzUiQ5sr0g23qdMvnKJQFfo7JuQn8vwAksxrQm6A0ByceEXSfyaBoVpFcTzEclxUomhY.NjAyMWJkZWUtMGMyOS00NmRkLThjZTMtODEyOTkzZTUyMTBi") {
                            map.cameraPosition = CameraPosition.Builder()
                                .target(LatLng(41.3784, 2.1815))
                                .zoom(12.4)
                                .build()
                            map.setMinZoomPreference(11.0)
                            map.setMaxZoomPreference(18.0)
                        }
                        addMarker(map)
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun addMarker(map : MapLibreMap){

}


