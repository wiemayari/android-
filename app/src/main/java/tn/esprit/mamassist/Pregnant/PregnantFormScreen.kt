package tn.esprit.mamassist.Pregnant

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun PregnantFormScreen(
    userName: String?,
    userAge: String?,
    onSubmit: (FormData) -> Unit,
    navController: NavHostController // Ajout du navController pour la navigation
) {
    var fullName by remember { mutableStateOf(userName ?: "") }
    var age by remember { mutableStateOf(userAge ?: "") }
    var lastPeriodDate by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var stressLevel by remember { mutableStateOf(5f) }
    var symptoms by remember { mutableStateOf("") }
    var eatingHabits by remember { mutableStateOf("") }
    var physicalActivity by remember { mutableStateOf("") }
    var medications by remember { mutableStateOf(false) }
    var medicationDetails by remember { mutableStateOf("") }
    var medicalHistory by remember { mutableStateOf(listOf("Diabète", "Hypertension", "Problèmes cardiaques")) }
    var selectedHistory by remember { mutableStateOf(setOf<String>()) }
    var notes by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState), // Activer le défilement
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Barre d'en-tête avec icône de fermeture
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Formulaire Santé Grossesse",
                style = MaterialTheme.typography.titleLarge
            )
            IconButton(onClick = { navController.navigate("yourSituation") }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close"
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Nom complet") },
            modifier = Modifier.fillMaxWidth(),
            enabled = userName == null
        )

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Âge") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            enabled = userAge == null
        )

        OutlinedTextField(
            value = lastPeriodDate,
            onValueChange = { lastPeriodDate = it },
            label = { Text("Dernière date de règles (jj/mm/aaaa)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Poids (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Taille (cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = "Antécédents médicaux")
        Column {
            medicalHistory.forEach { history ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = selectedHistory.contains(history),
                        onCheckedChange = {
                            if (selectedHistory.contains(history)) {
                                selectedHistory = selectedHistory - history
                            } else {
                                selectedHistory = selectedHistory + history
                            }
                        }
                    )
                    Text(text = history)
                }
            }
        }

        Text(text = "Niveau de stress actuel : ${stressLevel.toInt()}")
        Slider(
            value = stressLevel,
            onValueChange = { stressLevel = it },
            valueRange = 0f..10f,
            steps = 10,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = symptoms,
            onValueChange = { symptoms = it },
            label = { Text("Symptômes récents (séparez par des virgules)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = eatingHabits,
            onValueChange = { eatingHabits = it },
            label = { Text("Habitudes alimentaires") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = medications,
                onCheckedChange = { medications = it }
            )
            Text(text = "Prenez-vous des médicaments ?")
        }
        if (medications) {
            OutlinedTextField(
                value = medicationDetails,
                onValueChange = { medicationDetails = it },
                label = { Text("Détails des médicaments") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        OutlinedTextField(
            value = physicalActivity,
            onValueChange = { physicalActivity = it },
            label = { Text("Activité physique quotidienne") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Remarques supplémentaires") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val formData = FormData(
                    fullName,
                    age,
                    lastPeriodDate,
                    weight,
                    height,
                    stressLevel,
                    symptoms,
                    eatingHabits,
                    medications,
                    medicationDetails,
                    selectedHistory.toList(),
                    physicalActivity,
                    notes
                )
                onSubmit(formData)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Soumettre")
        }
    }
}

data class FormData(
    val fullName: String,
    val age: String,
    val lastPeriodDate: String,
    val weight: String,
    val height: String,
    val stressLevel: Float,
    val symptoms: String,
    val eatingHabits: String,
    val medications: Boolean,
    val medicationDetails: String,
    val medicalHistory: List<String>,
    val physicalActivity: String,
    val notes: String
)

@Preview(showBackground = true)
@Composable
fun PregnantFormScreenPreview() {
    PregnantFormScreen(
        userName = "Marie Dupont",
        userAge = "29",
        onSubmit = { formData ->
            println(formData)
        },
        navController = rememberNavController() // Pour prévisualisation
    )
}
