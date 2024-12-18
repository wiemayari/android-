@file:OptIn(ExperimentalMaterial3Api::class)

package tn.esprit.mamassist.Tools

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun TaskDetailsScreen(navController: NavHostController) {
    // Liste des tâches
    val tasks = listOf(
        "Register at the Maternity Health Care Center (MVC)",
        "Sign up Pregnancy Insurance",
        "Sign up for Free Pregnancy",
        "Submit pregnancy certificate to Försäkringskassan (week 20)",
        "Plan parental leave",
        "Apply for parental benefits",
        "Submit pregnancy certificate to Forsakringskassan(week 20)",
        "Plan parental benefits",
        "Inform your employer about the period of parental leave",
        "Write a birth plan"
    )

    // État des tâches cochées
    val checkedTasks = remember { mutableStateOf(setOf<String>()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "My pregnancy test is positive! What do I do now?",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent)
            )
        },
        bottomBar = {
            // Ajout du bouton en bas
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        // Ajouter l'action pour le bouton "+ New point"
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFAC50CC))
                ) {
                    Text(
                        text = "+ New point",
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            // Description
            Text(
                text = "We have gathered practicalities to check off after getting a positive pregnancy test!",
                style = TextStyle(fontSize = 16.sp, color = Color(0xFF514869)),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Barre de progression
            val progress = remember(checkedTasks.value.size, tasks.size) {
                checkedTasks.value.size / tasks.size.toFloat()
            }
            Text(text = "${(progress * 100).toInt()}%", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = Color(0xFFACD8AA)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Liste des tâches
            tasks.forEach { task ->
                TaskItem(
                    task = task,
                    isChecked = checkedTasks.value.contains(task),
                    onCheckedChange = { isChecked ->
                        checkedTasks.value = if (isChecked) {
                            checkedTasks.value + task
                        } else {
                            checkedTasks.value - task
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun TaskItem(task: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .toggleable(
                value = isChecked,
                onValueChange = { onCheckedChange(it) }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isChecked,
            onClick = null,
            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFFAC50CC))
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = task,
            style = TextStyle(fontSize = 16.sp, color = Color(0xFF514869)),
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskDetailsScreenPreview() {
    val navController = rememberNavController()
    TaskDetailsScreen(navController = navController)
}
