package tn.esprit.mamassist

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tn.esprit.mamassist.Authentification.connecte.LoginScreen
import tn.esprit.mamassist.Authentification.connecte.LoginViewModel
import tn.esprit.mamassist.Authentification.inscrire.RegisterScreen
import tn.esprit.mamassist.data.network.ApiClient
import tn.esprit.mamassist.data.repository.UserRepository

@Composable
fun AuthNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            val apiService = ApiClient.getApiService()
            val userRepository = UserRepository(apiService)
            val loginViewModel = LoginViewModel(userRepository)
            LoginScreen(
                viewModel = loginViewModel,
                onNavigateToRegister = { navController.navigate("register") },
                onLoginSuccess = { navController.navigate("main") } // Naviguer vers MainApp après login
            )
        }
        composable("register") {
            RegisterScreen(
                onNavigateToLogin = { navController.navigate("login") }
            )
        }
    }
}
