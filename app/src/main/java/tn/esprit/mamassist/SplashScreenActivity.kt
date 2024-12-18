package tn.esprit.mamassist

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import tn.esprit.mamassist.MainActivity
import tn.esprit.mamassist.ui.theme.MamAssistTheme

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MamAssistTheme {
                SplashScreen {
                    // Redirige vers MainActivity après le délai
                    startActivity(Intent(this, MainActivity::class.java))
                    finish() // Terminer l'activité de splash
                }
            }
        }
    }
}

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    var isLoading by remember { mutableStateOf(true) }

    // Simule un chargement
    LaunchedEffect(Unit) {
        delay(3000) // Attend 3 secondes
        isLoading = false
        onTimeout() // Appelle la fonction de redirection
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Image de fond
        Image(
            painterResource(id = R.drawable.waaaffuu),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Affiche la barre de progression
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    MamAssistTheme {
        SplashScreen(onTimeout = {})
    }
}
