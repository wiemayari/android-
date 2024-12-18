package tn.esprit.mamassist.BottomNavigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tn.esprit.mamassist.ContentScreen
import tn.esprit.mamassist.Tools.ToolsScreen

@Composable
fun BottomNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        modifier = Modifier.padding(paddingValues) // Gestion des marges pour éviter les chevauchements
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Tools.route) {
            ToolsScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Discussions.route) {
            ProfileScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Content.route) {
            ContentScreen(navController = navController)
        }
    }
}
