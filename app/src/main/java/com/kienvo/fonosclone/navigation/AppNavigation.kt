package com.kienvo.fonosclone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kienvo.fonosclone.screens.FonosHomeScreen
import com.kienvo.fonosclone.screens.PersonalScreen
import com.kienvo.fonosclone.screens.PlaceholderScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            FonosHomeScreen(navController)
        }

        composable(Screen.Search.route) {
            PlaceholderScreen("Tìm kiếm", navController)
        }

        composable(Screen.Library.route) {
            PlaceholderScreen("Thư viện", navController)
        }

        composable(Screen.Personal.route) {
            PersonalScreen(navController)
        }
    }
}
