package tn.esprit.mamassist

import MainInterface
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tn.esprit.mamassist.Authentification.InscrireScreenWithPager
import tn.esprit.mamassist.BottomNavigation.HomeScreen
import tn.esprit.mamassist.BottomNavigation.ProfileScreen
import tn.esprit.mamassist.Authentification.connecte.LoginScreen
import tn.esprit.mamassist.Authentification.connecte.LoginViewModel
import tn.esprit.mamassist.MamaAvecBebe.BabyForm
import tn.esprit.mamassist.MamaAvecBebe.MotherForm
import tn.esprit.mamassist.Pregnant.PregnantFormScreen
import tn.esprit.mamassist.Tools.DailyCheckInScreen
import tn.esprit.mamassist.Authentification.inscrire.RegisterScreen
import tn.esprit.mamassist.Tools.TaskDetailsScreen
import tn.esprit.mamassist.Tools.ToolsScreen
import tn.esprit.mamassist.data.network.ApiClient
import tn.esprit.mamassist.data.network.Baby
import tn.esprit.mamassist.data.repository.UserRepository
import tn.esprit.mamassist.doctor.DoctorProfileScreen
import tn.esprit.mamassist.doctor.HealthAppHomeScreen
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

    NavHost(navController = navController, startDestination = "pregnancyTracker") {
        composable("pregnancyTracker") {
            // This is the new interface for pregnancy tracking
            PregnancyTrackerScreen()
        }
        composable("inscrire") {
            InscrireScreenWithPager(navController = navController)
        }
        composable("login") {
            val apiService = ApiClient.getApiService()
            val userRepository = UserRepository(apiService)
            val loginViewModel = LoginViewModel(userRepository)
            LoginScreen(
                viewModel = loginViewModel,
                onNavigateToRegister = { navController.navigate("register") },
                onLoginSuccess = { navController.navigate("maininterface") }
            )
        }
        composable("register") {
            RegisterScreen(
                onNavigateToLogin = { navController.navigate("login") }
            )
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("tools") {
            ToolsScreen(navController = navController)
        }
        composable("content") {
            ContentScreen(navController = navController)
        }
        composable("profile") {
            ProfileScreen(navController = navController)
        }
        composable("yourSituation") {
            YourSituationScreen(
                onPregnantClick = { navController.navigate("pregnantForm") },
                onAlreadyHaveBabiesClick = { navController.navigate("motherForm") },
                onBackClick = { navController.navigate("home") }
            )
        }
        composable("pregnantForm") {
            PregnantFormScreen(
                userName = "Marie Dupont",
                userAge = "29",
                onSubmit = { formData ->
                    println("Formulaire soumis : $formData")
                },
                navController = navController
            )
        }
        composable("motherForm") {
            MotherForm(
                navController = navController,
                onAddBabyClick = { navController.navigate("babyForm") }
            )
        }
        composable("babyForm") {
            BabyForm(
                baby = Baby(),
                onDelete = { navController.popBackStack() }
            )
        }
        composable("dailyCheck") {
            DailyCheckInScreen(navController = navController)
        }
        composable("todo") {
            ToDoScreen(navController = navController)
        }
        composable("taskDetails") {
            TaskDetailsScreen(navController = navController)
        }
        composable("healthAppHome") {
            HealthAppHomeScreen(navController = navController)
        }
        composable("doctorProfile") {
            DoctorProfileScreen(navController = navController)
        }
        composable("maininterface") {
            MainInterface(navController = navController)        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MamAssistTheme {
        MainApp()
    }
}
