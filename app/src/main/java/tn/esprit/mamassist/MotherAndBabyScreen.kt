package tn.esprit.mamassist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MotherAndBabyScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    val cardWidth = 370.dp
    val totalPages = 2
    val currentPage = (scrollState.value / cardWidth.value).toInt()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF48FB1)) // Set the screen background to pink
            .padding(16.dp) // Add padding to ensure elements are not touching the edges of the screen
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Card for Mother
            Card(
                modifier = Modifier
                    .width(cardWidth)
                    .height(600.dp)
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("motherList") // Navigate on click
                    },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White) // Set card background to white
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp) // Add padding inside the card
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Learn more about your health",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black), // Text color to black
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp)) // Add space between elements
                    Text(
                        text = "This section will show details about the mother's health, tips, and more.",
                        style = TextStyle(fontSize = 14.sp, color = Color.Black), // Text color to black
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp)) // Add space between elements
                    Image(
                        painter = painterResource(id = R.drawable.userr), // Replace with your image
                        contentDescription = "Mother's Health",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(380.dp)
                            .padding(8.dp) // Add padding around the image
                    )
                }
            }

            // Card for Baby
            Card(
                modifier = Modifier
                    .width(cardWidth)
                    .height(600.dp)
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("babyList") // Navigate on click
                    },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White) // Set card background to white
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp) // Add padding inside the card
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "My baby",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black), // Text color to black
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp)) // Add space between elements
                    Text(
                        text = "This is what the baby looks like now!",
                        style = TextStyle(fontSize = 14.sp, color = Color.Black), // Text color to black
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp)) // Add space between elements
                    Image(
                        painter = painterResource(id = R.drawable.userr), // Replace with your image
                        contentDescription = "Baby's Health",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .padding(8.dp) // Add padding around the image
                    )
                }
            }
        }

        // Custom Scroll Dots
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            for (i in 0 until totalPages) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .padding(4.dp)
                        .background(
                            if (i == currentPage) Color.Blue else Color.Gray,
                            shape = CircleShape
                        )
                )
            }
        }

        // "What happens in Week 5" Text
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFFFADADD), shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "What happens in Week 5",
                color = Color.Black, // Text color to black
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMotherAndBabyScreen() {
    val navController = rememberNavController()
    MotherAndBabyScreen(navController = navController)
}