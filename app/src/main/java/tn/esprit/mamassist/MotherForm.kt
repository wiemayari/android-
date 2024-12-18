package tn.esprit.mamassist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import tn.esprit.mamassist.data.network.Baby

@Composable
fun MotherForm(onAddBabyClick: () -> Unit) {
    var motherName by remember { mutableStateOf("") }
    var motherAge by remember { mutableStateOf("") }
    var maritalStatus by remember { mutableStateOf("") }
    var profession by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var numberOfChildren by remember { mutableStateOf("") }
    var allergies by remember { mutableStateOf("") }
    var medicalFollowUp by remember { mutableStateOf("") }
    var medicalHistory by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Formulaire : Maman avec bébés", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = motherName,
            onValueChange = { motherName = it },
            label = { Text("Nom de la maman") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = motherAge,
            onValueChange = { motherAge = it },
            label = { Text("Âge de la maman") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = maritalStatus,
            onValueChange = { maritalStatus = it },
            label = { Text("Statut marital") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = profession,
            onValueChange = { profession = it },
            label = { Text("Profession") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Adresse") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { onAddBabyClick() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Ajouter un bébé")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                result = "Maman : $motherName\nÂge : $motherAge\n"
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Soumettre")
        }

        if (result.isNotEmpty()) {
            Text(text = result, modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMotherForm() {
    MotherForm(onAddBabyClick = {})
}
