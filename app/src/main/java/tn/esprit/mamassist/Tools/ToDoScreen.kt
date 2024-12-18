@file:OptIn(ExperimentalMaterial3Api::class)

package tn.esprit.mamassist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun ToDoScreen(navController: NavHostController) {
    var selectedTab by remember { mutableStateOf(0) }

    val tabs = listOf("Pregnant", "Child 0-1 years", "Child 1-2 years")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "To do",
                        style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFFCE9E5)) // Couleur de fond
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            val description = when (selectedTab) {
                0 -> "In these checklists there are tips and recommendations on what can be good to buy before the baby's arrival!"
                1 -> "In this category, we have collected checklists of things that can be good to have during the baby's first year. Remember that all babies are different and needs may vary - these are our recommendations!"
                2 -> "Here are some checklists to guide you through the second year of your child's life with practical tips!"
                else -> ""
            }

            Text(
                text = description,
                style = TextStyle(fontSize = 16.sp, color = Color(0xFF514869)),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                tabs.forEachIndexed { index, tab ->
                    Text(
                        text = tab,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedTab == index) Color.White else Color(0xFF514869)
                        ),
                        modifier = Modifier
                            .background(
                                color = if (selectedTab == index) Color(0xFFAC50CC) else Color.Transparent,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clickable { selectedTab = index }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }

            when (selectedTab) {
                0 -> ToDoListForPregnancy(navController)
                1 -> ToDoListForChild0To1Years(navController)
                2 -> ToDoListForChild1To2Years(navController)
            }
        }
    }
}

@Composable
fun ToDoListForPregnancy(navController: NavHostController) {
    val tasks = listOf(
        "My pregnancy test is positive! What do I do now?" to "0 of 8 completed",
        "Preparing for childbirth" to "0 of 6 completed",
        "Baby clothes" to "0 of 10 completed",
        "The hospital bag for the baby" to "0 of 9 completed",
        "The hospital bag for mom" to "0 of 12 completed"
    )

    ToDoList(tasks) { taskTitle ->
        if (taskTitle == "My pregnancy test is positive! What do I do now?") {
            navController.navigate("taskDetails")
        }
    }
}

@Composable
fun ToDoListForChild0To1Years(navController: NavHostController) {
    val tasks = listOf(
        "Practical (to do)" to "0 of 6 completed",
        "Baby Pharmacy" to "0 of 15 completed",
        "Packing list (travel with baby)" to "0 of 21 completed",
        "Small vacation pharmacy" to "0 of 10 completed",
        "For the nursery" to "0 of 9 completed"
    )

    ToDoList(tasks) {
        // Implémentez une action spécifique pour chaque tâche si nécessaire
    }
}

@Composable
fun ToDoListForChild1To2Years(navController: NavHostController) {
    val tasks = listOf(
        "Educational toys" to "0 of 3 completed",
        "First words practice" to "0 of 2 completed",
        "Health checkups" to "0 of 4 completed",
        "Outdoor activities" to "0 of 5 completed"
    )

    ToDoList(tasks) {
        // Implémentez une action spécifique pour chaque tâche si nécessaire
    }
}

@Composable
fun ToDoList(tasks: List<Pair<String, String>>, onTaskClick: (String) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        tasks.forEach { (title, subtitle) ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onTaskClick(title) },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(Color(0xFFAC50CC), shape = CircleShape)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = title,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF514869)
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = subtitle,
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Color(0xFF8E8E93)
                            )
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Go",
                        tint = Color(0xFFAC50CC)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoScreenPreview() {
    ToDoScreen(navController = rememberNavController())
}
