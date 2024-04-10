package com.example.artspace

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    FoodGallery()
                }
            }
        }
    }
}

@Composable
fun FoodGallery() {
    var currentArtworkPosition by remember { mutableStateOf(0) }

    val positions = listOf(
        Position(imageResource = R.drawable.torta, name = "Torta Ahogada", origin = "Jalisco"),
        Position(imageResource = R.drawable.chile, name = "Chile en nogada", origin = "Ciudad de México"),
        Position(imageResource = R.drawable.mole, name = "Mole Poblano", origin = "Puebla"),
        Position(imageResource = R.drawable.cochinita, name = "Cochinita Pibil", origin = "Yucatan"),
        Position(imageResource = R.drawable.pandecazon, name = "Pan de Cazon", origin = "Campeche")
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Surface(
            modifier = Modifier.fillMaxWidth()
               .padding(16.dp)

        ) {
            Title(text = "GASTRONOMÍA MEXICANA")
        }

        Surface(
            color = Color(0xFFFAE8B7),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(28.dp))
        ) {
            FoodImage(
                position = positions[currentArtworkPosition]
            )
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .height(76.dp)
        ) {
            FoodDescriptor(
                position = positions[currentArtworkPosition]
            )
        }

        Surface(
            modifier = Modifier.fillMaxWidth()

        ) {
            ButtonsRow(
                prevButtonText = "Anterior",
                nextButtonText = "Siguiente",
                onPrevClick = {
                    currentArtworkPosition = (currentArtworkPosition - 1 + positions.size) % positions.size
                },
                onNextClick = {
                    currentArtworkPosition = (currentArtworkPosition + 1) % positions.size
                }
            )
        }
    }
}



@Composable
fun Title(text: String) {
    Surface(
        color = Color(0xFFF6CB96), // Color ARGB: F6CB96
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(18.dp)), // Redondear los bordes del Surface
    ) {
        Text(
            text = text,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black, // Color del texto
            textAlign = TextAlign.Center, // Centrar el texto
            modifier = Modifier.padding(16.dp) // Agregar relleno al texto para separarlo del borde
        )
    }
}

@Composable
fun FoodImage(position: Position) {

    Image(
        painter = painterResource(id = position.imageResource),
        contentDescription = "Imagen del platillo: ${position.name}",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(300.dp)
            .clip(shape = RoundedCornerShape(10.dp))
    )
}


@Composable
fun FoodDescriptor(position: Position) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = " ${position.name}",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp)
        )
        Text(
            text = "Lugar de Origen: ${position.origin}",
            style = TextStyle(fontSize = 16.sp) // Tamaño de fuente más pequeño para el lugar de origen
        )
    }
}

@Composable
fun ButtonsRow(
    prevButtonText: String,
    nextButtonText: String,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(20.dp)
    ) {
        Button(
            onClick = onPrevClick,
        ) {
            Text(
                text = prevButtonText,
                fontSize = 20.sp, // Tamaño de fuente más grande
                color = Color.White, // Color del texto blanco
            )
        }
        Spacer(modifier = Modifier.width(35.dp))
        Button(
            onClick = onNextClick,
        ) {
            Text(
                text = nextButtonText,
                fontSize = 20.sp, // Tamaño de fuente más grande
                color = Color.White, // Color del texto blanco
            )
        }
    }
}



data class Position(val imageResource: Int, val name: String, val origin: String)


@Preview(showBackground = true)
@Composable
fun FoodGalleryPreview() {
    ArtSpaceTheme {
        FoodGallery()
    }
}
