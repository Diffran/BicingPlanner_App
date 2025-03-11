package org.diffran.bicingplanner.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DrawerDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.contentcapture.ContentCaptureManager.Companion.isEnabled
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.diffran.bicingplanner.R

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
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    var hora by remember { mutableStateOf(0) }
    var selectedSeason by remember { mutableStateOf("Estiu") }
    var selectedDay by remember { mutableStateOf("Laboral") }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        SegmentedButtonRow(
            onClickSeason = { selectedSeason = it },
            onClickDay = { selectedDay = it },
            onClickSearch = { /* Implementa la lògica de cerca si cal */ }//TODO: EL QUE FA EL BOTO DE LA LUPA
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
fun SegmentedButtonRow(
    onClickSeason : (String) -> Unit,
    onClickDay : (String) -> Unit,
    onClickSearch : () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        SingleChoiceSegmentedButton(
            onClick = { season -> onClickSeason(season) },
            options = listOf("Estiu", "Hivern")
        )
        Spacer(modifier = Modifier.width(2.dp))
        SingleChoiceSegmentedButton(
            onClick = { day -> onClickDay(day) },
            options = listOf("Laboral", "Festiu")
        )
        Spacer(modifier = Modifier.width(6.dp))
        Button(
            onClick = onClickSearch,
            shape = CircleShape,
            modifier = Modifier.size(48.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleChoiceSegmentedButton(modifier: Modifier = Modifier, onClick : (String) -> Unit, options : List<String>) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    SingleChoiceSegmentedButtonRow {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onClick = {
                    selectedIndex = index
                    onClick(label)
                },
                selected = index == selectedIndex,
                label = { Text(label, fontSize = 7.sp) },
            )
        }
    }
}


@Composable
fun SearchBar() {
    var text by remember { mutableStateOf(TextFieldValue()) }
    Row(
        modifier = Modifier
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(16.dp))
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
            .clickable { /*TODO*/ },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicTextField(
            modifier = Modifier
                .weight(8f)
                .padding(horizontal = 12.dp),
            value = text,
            onValueChange = {
                text = it
                //onTextChange(it.text)
            },
            textStyle = TextStyle(
                color = Color.Red.copy(alpha = 0.5f),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            decorationBox = { innerTextField ->
                if (text.text.isEmpty()) {
                    Text(
                        text = "número estació",
                        //color = Color.Gray.copy(alpha = 0.5f),
                        color = Color.Red.copy(alpha = 0.5f),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
                innerTextField()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = { /*TODO*/ }),
            singleLine = true
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .size(40.dp)
                .background(color = Color.Transparent, shape = CircleShape)
                .clickable {
                    if (text.text.isNotEmpty()) {
                        text = TextFieldValue(text = "")
                        // onTextChange("")
                    }
                },
        ) {
            if (text.text.isNotEmpty()) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    painter = painterResource(id = R.drawable.baseline_clear_24),
                    contentDescription = stringResource(R.string.search),
                    tint = Color.Red.copy(alpha = 0.5f),
                )
            } else {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = stringResource(R.string.search),
                    tint = Color.Red.copy(alpha = 0.5f),
                )
            }
        }
    }

}