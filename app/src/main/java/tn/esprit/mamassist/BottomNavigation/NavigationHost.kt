package tn.esprit.mamassist.BottomNavigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tn.esprit.mamassist.Authentification.connecte.LoginScreen
import tn.esprit.mamassist.Authentification.connecte.LoginViewModel
import tn.esprit.mamassist.Authentification.inscrire.RegisterScreen
import tn.esprit.mamassist.data.network.ApiClient
import tn.esprit.mamassist.data.repository.UserRepository

@Composable
fun NavigationHost(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = Modifier.padding(paddingValues)
    ) {
        // Graphe d'authentification
        composable("login") {
            val apiService = ApiClient.getApiService()
            val userRepository = UserRepository(apiService)
            val loginViewModel = LoginViewModel(userRepository)
            LoginScreen(
                viewModel = loginViewModel,
                onNavigateToRegister = { navController.navigate("register") },
                onLoginSuccess = { navController.navigate("main") }
            )
        }
        composable("register") {
            RegisterScreen(
                onNavigateToLogin = { navController.navigate("login") }
            )
        }

        // Graphe principal
        composable("main") {
            BottomNavGraph(navController = navController, paddingValues = paddingValues)
        }
    }
}
