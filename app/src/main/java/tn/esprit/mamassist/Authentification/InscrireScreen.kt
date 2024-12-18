package tn.esprit.mamassist.Authentification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.*
import tn.esprit.mamassist.R

@OptIn(ExperimentalPagerApi::class)
@Composable
fun InscrireScreenWithPager(navController: NavController) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Pager
        HorizontalPager(
            count = 2, // Nombre d'écrans
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            if (page == 0) {
                // Premier écran
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pregnant_woman), // Remplacez par votre ressource
                        contentDescription = "Image 1",
                        modifier = Modifier.size(500.dp)
                    )
                    Text(
                        text = "Enregistrez vos symptômes et mesures dans votre passeport de grossesse",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            } else {
                // Deuxième écran
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pregnant_network), // Remplacez par votre ressource
                        contentDescription = "Image 2",
                        modifier = Modifier.size(300.dp)
                    )
                    Text(
                        text = "Ayez recours à un suivi médical personnalisé de votre grossesse",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            }
        }

        // Points de pagination
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            repeat(2) { pageIndex ->
                val color = if (pagerState.currentPage == pageIndex) Color(0xFFFF4081) else Color.Gray
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .padding(4.dp)
                        .background(color, RoundedCornerShape(6.dp))
                )
            }
        }

        // Bouton S'inscrire
        Button(
            onClick = { navController.navigate("register") }, // Navigation vers "register"
            colors = ButtonDefaults.buttonColors(Color(0xFFFF4081)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "S'inscrire",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Texte Déjà inscrite ?
        Row(
            modifier = Modifier.padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Déjà inscrite ? ", color = Color.Gray)
            TextButton(onClick = { navController.navigate("login") }) { // Navigation vers "login"
                Text(text = "Se connecter", color = Color(0xFFFF4081))
            }
        }

        // Version
        Text(
            text = "Version 1.21.2.686-prod",
            color = Color.LightGray,
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInscrireScreenWithPager() {
    val navController = rememberNavController()
    InscrireScreenWithPager(navController = navController)
}
