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
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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




