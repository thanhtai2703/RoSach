package com.kienvo.fonosclone.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
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
import com.kienvo.fonosclone.screens.SearchScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation(navController: NavHostController) {
    // 1. Bọc toàn bộ NavHost bằng SharedTransitionLayout
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            // --- MÀN HÌNH HOME ---
            composable("home") {
                FonosHomeScreen(
                    navController = navController,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this
                )
            }

            // --- MÀN HÌNH CHI TIẾT ---
            composable(
                route = "detail/{bookId}",
                arguments = listOf(navArgument("bookId") { type = NavType.StringType })
            ) { backStackEntry ->
                val bookId = backStackEntry.arguments?.getString("bookId")
                BookDetailScreen(
                    navController = navController,
                    bookId = bookId,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this
                )
            }

            composable("search") { SearchScreen(navController = navController) }
            composable("library") { PlaceholderScreen("Thư viện", navController) }
            composable("personal") { PersonalScreen(navController) }
        }
    }
}