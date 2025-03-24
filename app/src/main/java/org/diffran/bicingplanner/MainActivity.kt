package org.diffran.bicingplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import org.diffran.bicingplanner.screen.FirstScreen
import org.diffran.bicingplanner.ui.theme.BicingPlannerTheme
import org.diffran.bicingplanner.viewModel.MainViewModel


class MainActivity : ComponentActivity() {

    lateinit var DEFAULT_STYLE_URL: String
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DEFAULT_STYLE_URL = getString(R.string.maplibre_style_url)

        val app = application as BicingApplication
        val factory = MainViewModel.AppViewModelFactory(app.repository)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        setContent {
            BicingPlannerTheme {
                FirstScreen(DEFAULT_STYLE_URL, viewModel)
            }
        }
    }
}




