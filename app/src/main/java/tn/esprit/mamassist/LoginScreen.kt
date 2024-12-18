package tn.esprit.mamassist

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import tn.esprit.mamassist.ui.theme.VSky
import java.util.regex.Pattern
@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val emailError = remember { mutableStateOf<String?>(null) }
    val passwordError = remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    val emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$")

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.loginscreen),
            contentDescription = "logo happy",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().background(Color.White.copy(alpha = 0.3f))
        )

        Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.2f)))

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(12.dp)) // Réduit l'espace avant le champ email

            // Email field with validation
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                placeholder = { Text(text = "Enter your email") },
                isError = emailError.value != null,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused) {
                            if (email.isEmpty()) {
                                emailError.value = "Email cannot be empty"
                            } else if (!emailPattern.matcher(email).matches()) {
                                emailError.value = "Please enter a valid email"
                            } else {
                                emailError.value = null
                            }
                        }
                    }
            )

            emailError.value?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 30.dp, bottom = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp)) // Réduit l'espace entre email et password

            // Password field with validation
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                placeholder = { Text(text = "Enter your password") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                isError = passwordError.value != null,
                modifier = Modifier.fillMaxWidth().padding(20.dp)
            )

            passwordError.value?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 30.dp, bottom = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Espacement supplémentaire avant le bouton Login

            // Login button
            Button(
                onClick = {
                    // Validate the fields before attempting login
                    if (email.isEmpty()) emailError.value = "Email cannot be empty"
                    if (password.isEmpty()) passwordError.value = "Password cannot be empty"

                    // If validation passes, attempt login
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        viewModel.loginUser(email, password)
                        onLoginSuccess()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = VSky),
            ) {
                Text(text = "Login", color = Color.White)
            }

            Spacer(modifier = Modifier.height(24.dp)) // Espacement entre Login et "Or login with..."

            Text(
                text = "Or, login with...",
                fontSize = 15.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .alpha(0.5f)
            )

            // Row for social login buttons (Google, Facebook, Apple)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                AuthOption(image = R.drawable.google, onClick = { /* Handle Google login */ })
                AuthOption(image = R.drawable.facebook, onClick = { /* Handle Facebook login */ })
                AuthOption(image = R.drawable.apple, onClick = { /* Handle Apple login */ })
            }

            Spacer(modifier = Modifier.height(28.dp)) // Espacement supplémentaire avant "Don't have an account?"

            // Navigation to sign-up screen
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally)
            ) {
                Text(text = "Don't have an account?", color = Color.Black)
                Text(
                    text = "Sign up",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.clickable {
                        onNavigateToRegister()
                    }
                )
            }
        }
    }
}

@Composable
fun AuthOption(image: Int, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "Auth option",
            modifier = Modifier.size(48.dp) // Ajustez la taille de l'icône
        )
    }
}