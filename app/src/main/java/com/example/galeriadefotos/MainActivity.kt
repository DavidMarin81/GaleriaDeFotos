package com.example.galeriadefotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galeriadefotos.ui.theme.GaleriaDeFotosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GaleriaDeFotosTheme {
                AppMain()
            }
        }
    }
}

@Composable
fun AppMain() {

    var result: Int by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .padding(32.dp)
    ) {
        when (result) {
            1 -> Photos(drawableResourceId = R.drawable.image01)
            2 -> Photos(drawableResourceId = R.drawable.image02)
            3 -> Photos(drawableResourceId = R.drawable.image03)
            else -> "No title"
        }

        when (result) {
            1 -> Description("Walking in the moon", "Dios Taboada", "2007")
            2 -> Description("A day in the museum", "Pablo Picasso", "1980")
            3 -> Description("Lost in space", "Louis Amstrong", "1975")
            else -> "No title"
        }

        PressButtons(result,
            presion = {
                result += it
                if(result > 3){
                    result = 1
                }
                if(result < 1){
                    result = 3
                }
            })
    }
}

@Composable
fun Photos(drawableResourceId: Int, modifier: Modifier = Modifier) {
    Image (
        painter = painterResource(drawableResourceId),
        contentDescription = null,
        modifier
            .size(400.dp)
    )
}

@Composable
fun Description(
    title: String,
    autor: String,
    date: String,
    modifier: Modifier = Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .border(1.dp, Color.Black)
            .fillMaxWidth()
    ) {
        Text(
            text = "\"$title\"",
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
        Row (
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text (
                text = autor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "($date)",
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun PressButtons(valorActual: Int, presion: (Int) -> Unit, modifier: Modifier = Modifier) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.Cyan),
        horizontalArrangement = Arrangement.SpaceAround

    ) {
        Button(onClick = { presion(-1) } ) {
            Text(
                text = "Anterior"
            )
            
        }
        Button(onClick = { presion(1) } ) {
            Text(
                text = "Siguiente"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GaleriaDeFotosTheme {
        AppMain()
    }
}