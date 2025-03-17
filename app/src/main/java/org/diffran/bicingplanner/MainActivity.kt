package org.diffran.bicingplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import org.diffran.bicingplanner.screen.FirstScreen
import org.diffran.bicingplanner.ui.theme.BicingPlannerTheme
import org.diffran.bicingplanner.viewModel.MainViewModel


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




