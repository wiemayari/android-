package tn.esprit.mamassist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tn.esprit.mamassist.BottomNavigation.BottomBar

@Composable
fun HealthAppHomeScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF4D92FF), Color.White)
                    )
                )
                .padding(padding)
                .padding(16.dp)
        ) {
            // Header Section
            HeaderSection()

            Spacer(modifier = Modifier.height(16.dp))

            // Search Bar
            SearchBar()

            Spacer(modifier = Modifier.height(16.dp))

            // Categories
            Text(
                text = "Categories",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            CategoriesSection()

            Spacer(modifier = Modifier.height(16.dp))

            // Recommended Doctors
            SectionWithSeeAll("Recommended Doctors")
            RecommendedDoctorsSection()

            Spacer(modifier = Modifier.height(16.dp))

            // Consultation Schedule
            SectionWithSeeAll("Consultation Schedule")
            ConsultationSchedule()
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Good Morning and stay healthy",
                color = Color.White,
                fontSize = 14.sp
            )
            Text(
                text = "Find your desired",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Doctor Right Now!",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notifications",
            tint = Color.White,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun SearchBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Search Doctor or Symptom",
                color = Color.Gray,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun CategoriesSection() {
    val categories = listOf(
        "Gynécologie", "Pédiatrie", "Endocrinologie",
        "Nutritionniste", "Psychologue", "Kinésithérapeute",
        "Cardiologie", "Pneumologie"
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(categories) { category ->
            Column(
                modifier = Modifier.clickable { /* Handle click */ },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color(0xFFEBF2FF), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = category.first().toString(),
                        fontSize = 20.sp,
                        color = Color(0xFF4D92FF)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = category,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun SectionWithSeeAll(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp)

        Text(
            text = "See All",
            fontSize = 14.sp,
            color = Color(0xFF9C27B0),
            modifier = Modifier.clickable { /* Handle click */ }
        )
    }
}

@Composable
fun RecommendedDoctorsSection() {
    val doctors = listOf(
        "Dr. Amelia Daniel\nDermatologist",
        "Dr. Erik Wagner\nUrology",
        "Dr. Benjamin Scott\nCardiology",
        "Dr. Sarah Johnson\nPediatrics",
        "Dr. Mark Spencer\nEndocrinology"
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(doctors) { doctor ->
            var isFavorite by remember { mutableStateOf(false) }

            Box(
                modifier = Modifier
                    .width(140.dp)
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(8.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(140.dp)
                            .background(Color.LightGray)
                            .clip(RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.doctor),
                            contentDescription = "Doctor Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite Icon",
                            tint = if (isFavorite) Color.Red else Color.Gray,
                            modifier = Modifier
                                .size(30.dp)
                                .padding(4.dp)
                                .clickable { isFavorite = !isFavorite }
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = doctor,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun ConsultationSchedule() {
    val doctors = listOf(
        "Dr. Jackson Moraes\nDermatology",
        "Dr. Amelia Daniel\nDermatologist",
        "Dr. Erik Wagner\nUrology"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(doctors) { doctor ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.doctor),
                        contentDescription = "Doctor Image",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(end = 16.dp)
                    )
                    Text(
                        text = doctor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHealthAppHomeScreen() {
    HealthAppHomeScreen(navController = rememberNavController())
}
