package org.diffran.bicingplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import org.diffran.bicingplanner.screen.MapScreen
import org.diffran.bicingplanner.ui.theme.BicingPlannerTheme
import org.diffran.bicingplanner.viewModel.MainViewModel
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.Style
import org.ramani.compose.MapLibre
import org.ramani.compose.Symbol


class MainActivity : ComponentActivity() {

    companion object {
        lateinit var DEFAULT_STYLE_URL: String
    }
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DEFAULT_STYLE_URL = getString(R.string.maplibre_style_url)

        setContent {
            BicingPlannerTheme {
                FirstScreen(DEFAULT_STYLE_URL, viewModel)
            }
        }
    }
}




