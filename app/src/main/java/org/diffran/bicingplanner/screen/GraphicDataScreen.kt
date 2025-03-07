package org.diffran.bicingplanner.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.diffran.bicingplanner.R

@Composable
fun GraphicsCard(modifier: Modifier, onExitClick: () -> Unit){//TODO: li ha d'entrar 100% el viewModel que es d'on rebrà les dades
    ElevatedCard (
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
    ){
        Box{
            IconButton(//exit X
                onClick = { onExitClick() },
                modifier = Modifier
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Tancar",
                    tint = Color.Black
                )
            }

            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Row(
                    modifier = Modifier.padding(20.dp),
                ){
                    Button(onClick = {}){ Text(text="EL") }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = {}){ Text(text = "MEC") }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = {}){ Text(text="DOC") }
                }
                Spacer(modifier = Modifier.height(20.dp))

                Graphic()
            }
        }

    }
}

@Composable
fun Graphic(){//TODO: haura de rebre el viewModel per agafar les ddes??
    Image(
        //TODO: mirar com posar gràfics...la Image es perque es vegi alguna cosa
        painter = painterResource(R.drawable.bicing_logo_mecanica),
        contentDescription = "Grafic"
    )
}