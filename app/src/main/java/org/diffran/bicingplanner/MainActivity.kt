package org.diffran.bicingplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.diffran.bicingplanner.MainActivity.Companion.DEFAULT_STYLE_URL
import org.diffran.bicingplanner.screen.FirstScreen
import org.diffran.bicingplanner.ui.theme.BicingPlannerTheme
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.Style
import org.ramani.compose.MapLibre
import org.ramani.compose.Symbol


class MainActivity : ComponentActivity() {

    companion object {
        lateinit var DEFAULT_STYLE_URL: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DEFAULT_STYLE_URL = getString(R.string.maplibre_style_url)

        setContent {
            BicingPlannerTheme {
               FirstScreen(DEFAULT_STYLE_URL)
            }
        }
    }
}



//TODO: ERASE
//val cameraPosition = rememberSaveable {
//    mutableStateOf(
//        org.ramani.compose.CameraPosition(
//            target = LatLng(41.3784, 2.1815),
//            zoom = 11.0,
//        )
//    )
//}
////prova de que podia carregar 500 simbols
//val startLat = 41.3700
//val startLng = 2.1800
//
//val symbols = (0 until 500).map { i ->
//    LatLng(startLat, startLng + i * 0.001) // Desplaça horitzontalment
//}
//val symbolCenter = rememberSaveable { mutableStateOf(LatLng(41.3784, 2.1815)) }
//val styleUrl = rememberSaveable { mutableStateOf(DEFAULT_STYLE_URL) }
//val styleBuilder = Style.Builder().fromUri(styleUrl.value)
//
//Box {
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        MapLibre(
//            modifier = Modifier.fillMaxSize(),
//            styleBuilder = styleBuilder,
//            cameraPosition = cameraPosition.value,
//        ) {
//
//            Symbol(
//                center = symbolCenter.value,
//                isDraggable = false,
//                imageId = R.drawable.pin_shadow,
//                size = 0.25f,
//            )
//            Symbol(
//                center = LatLng(41.3700, 2.1800),
//                imageId = R.drawable.bicing_logo_docks,
//                size = 0.1f,
//                isDraggable = false,
//            )
//
//
//            symbols.forEach { position ->
//                Symbol(
//                    center = position,
//                    imageId = R.drawable.bicing_logo_docks,
//                    size = 0.1f,
//                    isDraggable = false
//                )
//            }
//        }
//    }
//}



