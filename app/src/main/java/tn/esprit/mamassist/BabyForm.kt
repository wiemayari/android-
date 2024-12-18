package tn.esprit.mamassist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tn.esprit.mamassist.data.network.Baby


@Composable
fun BabyForm(baby: Baby, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Informations du bébé", style = MaterialTheme.typography.titleSmall)

            OutlinedTextField(
                value = baby.name,
                onValueChange = { baby.name = it },
                label = { Text("Nom du bébé") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = baby.age,
                onValueChange = { baby.age = it },
                label = { Text("Âge du bébé (mois)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = baby.weight,
                onValueChange = { baby.weight = it },
                label = { Text("Poids (kg)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = baby.height,
                onValueChange = { baby.height = it },
                label = { Text("Taille (cm)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = baby.behavior,
                onValueChange = { baby.behavior = it },
                label = { Text("Comportement particulier") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = baby.healthIssues,
                onValueChange = { baby.healthIssues = it },
                label = { Text("Problèmes de santé") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = onDelete,
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Supprimer", color = MaterialTheme.colorScheme.onError)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBabyForm() {
    BabyForm(
        baby = Baby(name = "Exemple", age = "12", weight = "10", height = "80", behavior = "Calme", healthIssues = "Aucun"),
        onDelete = {}
    )
}