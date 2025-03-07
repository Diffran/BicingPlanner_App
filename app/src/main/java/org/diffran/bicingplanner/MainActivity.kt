package org.diffran.bicingplanner

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import org.diffran.bicingplanner.ui.component.Pin
import org.diffran.bicingplanner.ui.theme.BicingPlannerTheme
import org.maplibre.android.MapLibre
import org.maplibre.android.annotations.Icon
import org.maplibre.android.annotations.IconFactory
import org.maplibre.android.annotations.Marker
import org.maplibre.android.annotations.MarkerOptions
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.Style
import org.ramani.compose.Circle
import org.ramani.compose.MapLibre
import org.ramani.compose.Polyline
import org.ramani.compose.Symbol


class MainActivity : ComponentActivity() {
    //prova

    companion object {
        lateinit var DEFAULT_STYLE_URL: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DEFAULT_STYLE_URL = getString(R.string.maplibre_style_url)

        setContent {
            BicingPlannerTheme {
                val cameraPosition = rememberSaveable {
                    mutableStateOf(
                        org.ramani.compose.CameraPosition(
                            target = LatLng(41.3784, 2.1815),
                            zoom = 11.0,
                        )
                    )
                }
                val symbolCenter = rememberSaveable { mutableStateOf(LatLng(41.3784, 2.1815)) }
                val circleCenter = rememberSaveable { mutableStateOf(LatLng(4.8, 46.0)) }
                val isDefaultStyle = rememberSaveable { mutableStateOf(true) }
                val styleUrl = rememberSaveable { mutableStateOf(DEFAULT_STYLE_URL) }
                val styleBuilder = Style.Builder().fromUri(styleUrl.value)

                Box {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MapLibre(
                            modifier = Modifier.fillMaxSize(),
                            styleBuilder = styleBuilder,
                            cameraPosition = cameraPosition.value,
                        ) {

                            Symbol(
                                center = symbolCenter.value,
                                isDraggable = false,
                                imageId = R.drawable.pin_shadow,
                                onSymbolDragged = { center -> symbolCenter.value = center }
                            )
                            Symbol(
                                center = LatLng(41.3700, 2.1800),
                                isDraggable = false,
                                onSymbolDragged = { center -> symbolCenter.value = center }
                            )
                            Circle(
                                center = circleCenter.value,
                                radius = 50F,
                                isDraggable = true,
                                borderWidth = 2F,
                                onCenterDragged = { center -> circleCenter.value = center }
                            )
                        }
                    }
                    Button(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        onClick = {
                            styleUrl.value =
                                if (!isDefaultStyle.value) DEFAULT_STYLE_URL
                                else resources.getString(R.string.maplibre_style_url)

                            isDefaultStyle.value = !isDefaultStyle.value
                        }) {
                        Text("Swap style")
                    }
                }
            }
        }
    }
}


