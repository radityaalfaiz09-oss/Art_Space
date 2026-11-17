package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

data class Artwork(
    val image: Int,
    val title: String,
    val artist: String,
    val description: String
)

@Composable
fun ArtSpaceApp() {

    val artworks = listOf(
        Artwork(
            R.drawable.the_kiss,
            "The Kiss",
            "Gustav Klimt (1907-1908)",
            "Dua sosok berpelukan di atas padang bunga dan terbungkus pola emas yang " +
                    "membuat mereka tampak menyatu. Dibuat pada masa \"periode emas\" Klimt, " +
                    "saat ia banyak terinspirasi mosaik Bizantium."
        ),
        Artwork(
            R.drawable.great_wave,
            "The Great Wave",
            "Katsushika Hokusai (1831)",
            "Cetakan balok kayu Jepang yang menggambarkan ombak raksasa di atas tiga perahu " +
                    "nelayan, dengan Gunung Fuji kecil di kejauhan. Termasuk dalam seri " +
                    "\"Tiga Puluh Enam Pemandangan Gunung Fuji\"."
        ),
        Artwork(
            R.drawable.water_lilies,
            "Water Lilies",
            "Claude Monet (1906)",
            "Pemandangan kolam teratai di taman Monet di Giverny. Monet melukis tema ini " +
                    "ratusan kali untuk menangkap bagaimana cahaya mengubah suasana kolam " +
                    "sepanjang hari."
        ),
        Artwork(
            R.drawable.the_scream,
            "The Scream",
            "Edvard Munch (1893)",
            "Sosok yang memegangi kepalanya di atas jembatan dengan langit senja yang " +
                    "bergelombang. Munch terinspirasi dari pengalamannya merasakan kecemasan " +
                    "yang seolah memenuhi seluruh alam."
        ),
        Artwork(
            R.drawable.birth_of_venus,
            "The Birth of Venus",
            "Sandro Botticelli (1485)",
            "Venus, dewi cinta, tiba di tepi laut di atas cangkang kerang. Karya Renaisans " +
                    "Italia ini kini dipajang di Galeri Uffizi, Florence."
        )
    )

    var currentIndex by remember { mutableIntStateOf(0) }
    val currentArtwork = artworks[currentIndex]

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFE9E6F0)
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(380.dp)
                        .shadow(elevation = 12.dp, shape = RoundedCornerShape(2.dp))
                        .background(Color.White, RoundedCornerShape(2.dp))
                        .padding(28.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = currentArtwork.image),
                        contentDescription = currentArtwork.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF9A9AA0), RoundedCornerShape(4.dp))
                        .padding(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = currentArtwork.title,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFF1E1E1E)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = currentArtwork.artist,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E1E1E)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = currentArtwork.description,
                        fontSize = 13.sp,
                        lineHeight = 19.sp,
                        color = Color(0xFF2A2A2A)
                    )
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Button(
                    onClick = {
                        currentIndex =
                            if (currentIndex == 0) artworks.lastIndex
                            else currentIndex - 1
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(46.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6B5BA8)
                    )
                ) {
                    Text(text = "Previous", fontSize = 14.sp, color = Color.White)
                }

                Button(
                    onClick = {
                        currentIndex = (currentIndex + 1) % artworks.size
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(46.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6B5BA8)
                    )
                ) {
                    Text(text = "Next", fontSize = 14.sp, color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}