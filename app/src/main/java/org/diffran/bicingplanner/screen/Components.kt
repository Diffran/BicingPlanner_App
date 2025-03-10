package org.diffran.bicingplanner.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun LateralButtons(modifier: Modifier, onElectricClick:() -> Unit, onMecanicClick:()->Unit, onDockClick:()->Unit){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        //TODO: canviar per icones electrica, mecanica i dock/anclaje
        FloatingActionButton(onClick = {onElectricClick()}, shape = CircleShape) {
            Text("EL")
        }
        FloatingActionButton(onClick = { onMecanicClick() }, shape = CircleShape) {
            Text("MEC")
        }
        FloatingActionButton(onClick = { onDockClick()}, shape = CircleShape) {
            Text("DOCK")
        }
    }
}

@Composable
fun ControllerButtons(){
    var isWinter by remember { mutableStateOf(false) }
    var isWeekend by remember { mutableStateOf(false) }
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    var hora by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        SwitchersRow(
            isWinter = isWinter,
            isWeekend = isWeekend,
            onIsWinterChange = { isWinter = it },
            onIsWeekendChange = { isWeekend = it }
        )

        Slider(
            modifier = Modifier.padding(),
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f .. 23f,
            steps = 22
        )

        hora = sliderPosition.toInt()
        Text(text = "$hora:00")
    }
}

@Composable
fun SwitchersRow(
    isWinter: Boolean,
    isWeekend: Boolean,
    onIsWinterChange: (Boolean) -> Unit,
    onIsWeekendChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        SwitchIcon(imageVector = Icons.Filled.AddCircle)//TODO: SHAN DE CANVIAR PER ADEQUATS
        Switch(
            checked = isWinter,
            onCheckedChange = onIsWinterChange,
        )
        SwitchIcon(imageVector = Icons.Filled.AddCircle)

        Spacer(modifier = Modifier.width(24.dp))

        SwitchIcon(imageVector = Icons.Filled.AddCircle)
        Switch(
            checked = isWeekend,
            onCheckedChange = onIsWeekendChange,
        )
        SwitchIcon(imageVector = Icons.Filled.AddCircle)
    }
}

@Composable
fun SwitchIcon(imageVector: ImageVector){
    Icon(
        imageVector = imageVector,
        contentDescription = "icona",
        tint = Color.Black
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloatingSearchBar() {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Label") }
    )
}

