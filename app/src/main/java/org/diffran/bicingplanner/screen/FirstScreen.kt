package org.diffran.bicingplanner.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FirstScreen(mapStyle: String){
    var showGraphics by remember { mutableStateOf(false) }
    var searchType by remember { mutableStateOf("EL") }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(170.dp)
            ) {
                ControllerButtons()
            }
        }
    ) {paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {

            MapScreen(mapStyle)

            LateralButtons(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(16.dp),
                onElectricClick = {searchType = "EL"},
                onMecanicClick = {searchType = "MEC"},
                onDockClick = {searchType = "DOCK"},
            )


            if(showGraphics){
                //TODO: which marker??
                GraphicsCard(modifier = Modifier
                    .padding(16.dp)
                    .height(350.dp)
                    .align(Alignment.Center),
                    onExitClick = {showGraphics = false})
            }
        }
    }
}
