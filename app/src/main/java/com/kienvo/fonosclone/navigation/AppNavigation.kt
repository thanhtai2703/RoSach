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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation(navController: NavHostController) {
    // 1. Bọc toàn bộ NavHost bằng SharedTransitionLayout
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = "home" // Đảm bảo route này khớp với bên dưới
        ) {
            // --- MÀN HÌNH HOME ---
            composable("home") {
                FonosHomeScreen(
                    navController = navController,
                    // Truyền 2 phép thuật này xuống để Home dùng
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
                    // Truyền 2 phép thuật này xuống để Detail dùng
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this
                )
            }

            // Các màn hình phụ khác (Không cần Animation đặc biệt thì giữ nguyên)
            composable("search") { PlaceholderScreen("Tìm kiếm", navController) }
            composable("library") { PlaceholderScreen("Thư viện", navController) }
            composable("personal") { PersonalScreen(navController) }
        }
    }
}