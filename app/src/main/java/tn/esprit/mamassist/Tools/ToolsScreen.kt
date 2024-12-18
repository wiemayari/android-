package tn.esprit.mamassist.Tools

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tn.esprit.mamassist.BottomNavigation.BottomBar
import tn.esprit.mamassist.R
import tn.esprit.mamassist.ui.theme.Pink40

@Composable
fun ToolsScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ToolsHeader()
            ToolsCarousel(navController = navController)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Tools",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF514869)
                ),
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 8.dp)
            )
            ToolsGrid(navController = navController)
        }
    }
}

@Composable
fun ToolsHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Pink40)
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Tools",
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
    }
}

@Composable
fun ToolsCarousel(navController: NavHostController) {
    var currentPage by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Pink40)
            .padding(bottom = 16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                item {
                    CarouselCard(
                        imageRes = R.drawable.baby,
                        title = "Have you written your birth letter?"
                    )
                }
                item {
                    MoodCard(navController = navController)
                }
                item {
                    ChecklistCard(navController = navController)
                }
            }

            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(3) { page ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(
                                if (page == currentPage) Color.Black else Color.LightGray
                            )
                            .clickable { currentPage = page }
                    )
                }
            }
        }
    }
}

@Composable
fun CarouselCard(imageRes: Int, title: String) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(180.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = title,
                color = Color.White,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.Black.copy(alpha = 0.5f), shape = RoundedCornerShape(8.dp))
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun ChecklistCard(navController: NavHostController) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(180.dp)
            .padding(0.dp)
            .clickable { navController.navigate("todo") }, // Navigation vers ToDoScreen
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F9FF))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.check),
                contentDescription = "Check Icon",
                modifier = Modifier
                    .size(60.dp)
                    .weight(1f),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Checklists for you!",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF514869)
                    )
                )
                Text(
                    text = "Plan better with our detailed checklists",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF514869)
                    ),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}


@Composable
fun MoodCard(navController: NavHostController) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(180.dp)
            .padding(0.dp)
            .clickable { navController.navigate("dailyCheck") }, // Navigation vers DailyCheck
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFBEFF7))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "How are you today?",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF514869)
                )
            )

            Text(
                text = "Track your mood & get personal support",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF514869)
                ),
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.emoji),
                contentDescription = "Mood icons",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )
        }
    }
}

@Composable
fun ToolsGrid(navController: NavHostController) {
    val tools = listOf(
        "Checklist" to R.drawable.checklist,
        "Q&A" to R.drawable.qa,
        "Daily Mood" to R.drawable.mood,
        "Pre-parenting Conversations" to R.drawable.conversation,
        "Guides" to R.drawable.guides,
        "Dietary Advice" to R.drawable.diet,
    )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(tools.chunked(3)) { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                rowItems.forEach { (label, iconRes) ->
                    ToolCard(label = label, iconRes = iconRes, navController = navController)
                }
            }
        }
    }
}

@Composable
fun ToolCard(label: String, iconRes: Int, navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(100.dp)
            .clickable { if (label == "Checklist") navController.navigate("todo") }
    ) {
        Surface(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            color = Pink40
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            style = TextStyle(fontSize = 14.sp, color = Color(0xFF514869))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ToolsScreenPreview() {
    val navController = rememberNavController()
    ToolsScreen(navController = navController)
}
