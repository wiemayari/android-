package tn.esprit.mamassist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MotherListScreen(navController: NavController? = null) {
    // Liste des jalons avec images
    val items = listOf(
        Pair(R.drawable.baby, "Milestone 1: Smiling - Your baby starts to smile."),
        Pair(R.drawable.baby, "Milestone 2: Crawling - Your baby begins to crawl."),
        Pair(R.drawable.baby, "Milestone 3: Walking - Your baby takes their first steps."),
        Pair(R.drawable.baby, "Milestone 4: Talking - Your baby starts saying their first words.")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Mother Milestones", color = Color.White) },
                navigationIcon = {
                    if (navController != null) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF4CAF50))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()) // Rendre la colonne défilable
        ) {
            // Itérer sur les éléments et les afficher dans une carte
            for (item in items) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), // Espacement entre les cartes
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)) // Fond clair pour la carte
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth() // Assurer une largeur complète pour le contenu
                    ) {
                        // Image en haut de la carte
                        Image(
                            painter = painterResource(id = item.first),
                            contentDescription = item.second,
                            contentScale = ContentScale.Crop, // Recadrer l'image pour remplir la largeur
                            modifier = Modifier
                                .fillMaxWidth() // L'image s'étend sur toute la largeur de la carte
                                .height(200.dp) // Définir une hauteur fixe pour l'image
                        )

                        // Texte sous l'image
                        Text(
                            text = item.second,
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
                            modifier = Modifier
                                .padding(16.dp) // Marges autour du texte
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center // Centrer le texte
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Mother List Preview")
@Composable
fun MotherListScreenPreview() {
    MotherListScreen(navController = rememberNavController())
}
