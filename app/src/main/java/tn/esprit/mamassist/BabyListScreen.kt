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
fun BabyListScreen(navController: NavController? = null) {
    // List of milestones with images
    val items = listOf(
        Pair(R.drawable.baby, "Milestone 1: Smiling - Your baby starts to smile as a response to your face or voice."),
        Pair(R.drawable.baby, "Milestone 2: Crawling - Your baby begins to crawl and explore their surroundings."),
        Pair(R.drawable.baby, "Milestone 3: Walking - Your baby takes their first independent steps."),
        Pair(R.drawable.baby, "Milestone 4: Talking - Your baby starts saying their first words.")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Baby Milestones", color = Color.White) },
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
                .verticalScroll(rememberScrollState()) // Make the column scrollable
        ) {
            // Iterate over the items and display each one in a card
            for (item in items) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), // Space between cards
                    shape = MaterialTheme.shapes.medium, // Rounded corners
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)) // Light gray background for the card
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth() // Ensure full width for the card content
                    ) {
                        // Image at the top of the card
                        Image(
                            painter = painterResource(id = item.first),
                            contentDescription = item.second,
                            contentScale = ContentScale.Crop, // Crop the image to fill the width
                            modifier = Modifier
                                .fillMaxWidth() // Image spans the entire width of the card
                                .height(200.dp) // Set a fixed height for the image
                        )

                        // Text under the image
                        Text(
                            text = item.second,
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
                            modifier = Modifier
                                .padding(16.dp) // Padding around the text
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center // Align the text to the center
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BabyListScreenPreview() {
    BabyListScreen(navController = rememberNavController())
}
