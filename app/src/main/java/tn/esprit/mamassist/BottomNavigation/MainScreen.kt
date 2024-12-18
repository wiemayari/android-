package tn.esprit.mamassist.BottomNavigation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tn.esprit.mamassist.BottomNavigation.BottomNavGraph
import tn.esprit.mamassist.BottomNavigation.BottomBar
import tn.esprit.mamassist.LoginScreen
import tn.esprit.mamassist.LoginViewModel
import tn.esprit.mamassist.RegisterScreen
import tn.esprit.mamassist.data.network.ApiClient
import tn.esprit.mamassist.data.repository.UserRepository
import tn.esprit.mamassist.ui.theme.MamAssistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MamAssistTheme {
                MainApp()
            }
        }
    }
}
@Composable
fun MainApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            val apiService = ApiClient.getApiService()
            val userRepository = UserRepository(apiService)
            val loginViewModel = LoginViewModel(userRepository)
            LoginScreen(
                viewModel = loginViewModel,
                onNavigateToRegister = { navController.navigate("register") },
                onLoginSuccess = { navController.navigate("home") }
            )
        }
        composable("register") {
            RegisterScreen(
                onNavigateToLogin = { navController.navigate("login") }
            )
        }
        composable("home") {
            // Passez le navController à HomeScreen
            HomeScreen(navController = navController)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MamAssistTheme {
        MainApp()  // Aperçu de l'application principale
    }
}
