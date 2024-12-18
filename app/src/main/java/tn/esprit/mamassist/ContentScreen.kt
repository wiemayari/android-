package tn.esprit.mamassist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tn.esprit.mamassist.BottomNavigation.BottomBar

@Composable
fun ContentScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) } // Ajout de la BottomBar
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFF4EB)) // Couleur de fond pêche pastel
                .verticalScroll(rememberScrollState()) // Permettre le défilement vertical
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Titre principal
            Text(
                text = "Content",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color(0xFF6A4C93), // Violet doux
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textAlign = TextAlign.Center
            )

            // Barre de recherche
            SearchhBar()

            Spacer(modifier = Modifier.height(16.dp))

            // Section "Recommended for you"
            Text(
                text = "Recommended for you",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF6A4C93), // Violet doux
                modifier = Modifier.padding(vertical = 8.dp)
            )

            RecommendedSection()

            Spacer(modifier = Modifier.height(16.dp))

            // Section "Pregnancy"
            CategorySection(
                title = "Pregnancy",
                items = listOf(
                    Triple(R.drawable.bebe, "Health", "Details about health"),
                    Triple(R.drawable.bebe, "Pregnancy issues", "Tips for pregnancy issues"),
                    Triple(R.drawable.bebe, "Pelvic pain", "Pelvic pain guidance"),
                    Triple(R.drawable.bebe, "Childbirth", "Childbirth tips"),
                    Triple(R.drawable.bebe, "Twins", "Tips for having twins"),
                    Triple(R.drawable.bebe, "Workout", "Workouts during pregnancy")
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Section "Child & Parent"
            CategorySection(
                title = "Child & Parent",
                items = listOf(
                    Triple(R.drawable.bebe, "Postpartum", "Postpartum care"),
                    Triple(R.drawable.bebe, "Newborn", "Caring for a newborn"),
                    Triple(R.drawable.bebe, "Sleep", "Baby sleeping tips"),
                    Triple(R.drawable.bebe, "Children's health", "Health tips for children"),
                    Triple(R.drawable.bebe, "Breastfeeding", "Tips for breastfeeding"),
                    Triple(R.drawable.bebe, "Twins", "Caring for twins"),
                    Triple(R.drawable.bebe, "Children and food", "Healthy food for children"),
                    Triple(R.drawable.bebe, "Workout", "Exercises for parents"),
                    Triple(R.drawable.bebe, "Other", "Other resources")
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchhBar() {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color(0xFF6A4C93) // Violet doux
            )
        },
        placeholder = { Text(text = "Search Baby Journey", color = Color(0xFFA08ABF)) },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(28.dp)
    )
}

@Composable
fun RecommendedSection() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(2) { index ->
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.size(200.dp, 150.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE7D8)) // Couleur des cartes
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bebe),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp),
                        alignment = Alignment.TopCenter
                    )
                    Text(
                        text = if (index == 0) "Eating disorders during pregnancy" else "Which cheeses can you eat during pregnancy?",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = Color(0xFF6A4C93), // Violet doux
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CategorySection(title: String, items: List<Triple<Int, String, String>>) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = Color(0xFF6A4C93), // Violet doux
        modifier = Modifier.padding(vertical = 8.dp)
    )

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        for (i in items.chunked(3)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                i.forEach { item ->
                    CategoryItem(iconId = item.first, title = item.second)
                }
            }
        }
    }
}

@Composable
fun CategoryItem(iconId: Int, title: String) {
    Column(
        modifier = Modifier
            .size(100.dp)
            .background(Color(0xFFFFE7D8), RoundedCornerShape(12.dp)), // Couleur des boîtes
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = title,
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = Color(0xFF6A4C93), // Violet doux
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContentScreenPreview() {
    val fakeNavController = rememberNavController()
    ContentScreen(navController = fakeNavController)
}
