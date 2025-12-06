package com.kienvo.fonosclone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kienvo.fonosclone.screens.BookDetailScreen
import com.kienvo.fonosclone.screens.FonosHomeScreen
import com.kienvo.fonosclone.screens.PersonalScreen
import com.kienvo.fonosclone.screens.PlaceholderScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable("home") { FonosHomeScreen(navController) }

        composable(
            route = "detail/{bookId}", // Nhận tham số bookId
            arguments = listOf(navArgument("bookId") { type = NavType.StringType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId")
            BookDetailScreen(navController, bookId)
        }

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
