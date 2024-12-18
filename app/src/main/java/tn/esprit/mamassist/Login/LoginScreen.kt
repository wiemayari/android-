package tn.esprit.mamassist.Login

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tn.esprit.mamassist.R
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
    var passwordFocused by remember { mutableStateOf(false) }
    val loginUiState by viewModel.loginUiState.observeAsState(LoginUiState())

    val emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$")

    Box(modifier = Modifier.fillMaxSize()) {
        // Image de fond
        Image(
            painter = painterResource(id = R.drawable.logggin),
            contentDescription = "logo happy",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Contenu principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Espacement entre l'image et les champs
            Spacer(modifier = Modifier.height(120.dp))

            // Champ email
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

            Spacer(modifier = Modifier.height(8.dp)) // Espacement entre email et password

            // Champ mot de passe
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                placeholder = { Text(text = "Enter your password") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                isError = passwordError.value != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .onFocusChanged { focusState ->
                        passwordFocused = focusState.isFocused
                        if (!focusState.isFocused && password.isEmpty()) {
                            passwordError.value = "Password cannot be empty"
                        } else if (!focusState.isFocused && password.length < 6) {
                            passwordError.value = "Password must be at least 6 characters"
                        } else if (focusState.isFocused) {
                            passwordError.value = null
                        }
                    }
            )

            passwordError.value?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 30.dp, bottom = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Espacement avant le bouton Login

            // Bouton de connexion
            Button(
                onClick = {
                    if (email.isEmpty()) emailError.value = "Email cannot be empty"
                    if (password.isEmpty()) passwordError.value = "Password cannot be empty"
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

            // Lien "Mot de passe oublié"
            Text(
                "Forgot password",
                color = Color(0xFF2980B9),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 135.dp)
                    .clickable(onClick = { /* Handle password reset */ })
            )

            Spacer(modifier = Modifier.height(24.dp)) // Espacement avant "Or login with..."

            // Texte "Ou connectez-vous avec"
            Text(
                text = "Or, login with...",
                fontSize = 15.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .alpha(0.5f)
            )

            // Boutons de connexion sociale
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                AuthOption(image = R.drawable.google, onClick = { /* Handle Google login */ })
                AuthOption(image = R.drawable.facebook, onClick = { /* Handle Facebook login */ })
                AuthOption(image = R.drawable.apple, onClick = { /* Handle Apple login */ })
            }

            Spacer(modifier = Modifier.height(28.dp)) // Espacement avant le lien Sign Up

            // Lien pour aller à l'écran d'inscription
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
            modifier = Modifier.size(48.dp)
        )
    }
}
