package com.kienvo.fonosclone.navigation

import android.annotation.SuppressLint // [1] Nhớ Import cái này
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.kienvo.fonosclone.screens.ActiveSearchScreen
import com.kienvo.fonosclone.screens.BookDetailScreen
import com.kienvo.fonosclone.screens.FonosHomeScreen
import com.kienvo.fonosclone.screens.PersonalScreen
import com.kienvo.fonosclone.screens.PlaceholderScreen
import com.kienvo.fonosclone.screens.SearchScreen
import com.kienvo.fonosclone.widgets.BottomBar

private val mainTabs = listOf("home", "search", "library", "personal")

private fun getTabIndex(route: String?): Int {
    return mainTabs.indexOf(route)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter") // [2] THÊM DÒNG NÀY ĐỂ HẾT BÁO LỖI
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation(navController: NavHostController) {
    val animDuration = 300
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBottomBar = currentRoute in mainTabs

    SharedTransitionLayout {
        Scaffold(
            bottomBar = {
                if (showBottomBar) {
                    BottomBar(navController)
                }
            },
            containerColor = Color.Transparent
        ) { innerPadding -> // [3] Vẫn giữ biến innerPadding ở đây nhưng KHÔNG DÙNG bên dưới

            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier
                    .fillMaxSize()
                // [4] QUAN TRỌNG: Không set padding bottom ở đây nữa.
                // Nội dung sẽ tràn xuống tận đáy màn hình, nằm ĐÈ DƯỚI BottomBar.
                // Nhờ vậy mà các góc bo tròn của BottomBar sẽ hiển thị đúng màu nền của trang (không bị trắng/đen).
                ,

                // ... Phần Animation bên dưới giữ nguyên ...
                enterTransition = {
                    val fromIndex = getTabIndex(initialState.destination.route)
                    val toIndex = getTabIndex(targetState.destination.route)
                    if (fromIndex != -1 && toIndex != -1) {
                        if (toIndex > fromIndex) slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(animDuration))
                        else slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(animDuration))
                    } else fadeIn(tween(animDuration))
                },
                exitTransition = {
                    val fromIndex = getTabIndex(initialState.destination.route)
                    val toIndex = getTabIndex(targetState.destination.route)
                    if (fromIndex != -1 && toIndex != -1) {
                        if (toIndex > fromIndex) slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(animDuration))
                        else slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(animDuration))
                    } else fadeOut(tween(animDuration))
                },
                popEnterTransition = {
                    val fromIndex = getTabIndex(initialState.destination.route)
                    val toIndex = getTabIndex(targetState.destination.route)
                    if (fromIndex != -1 && toIndex != -1) {
                        if (toIndex > fromIndex) slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(animDuration))
                        else slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(animDuration))
                    } else fadeIn(tween(animDuration))
                },
                popExitTransition = {
                    val fromIndex = getTabIndex(initialState.destination.route)
                    val toIndex = getTabIndex(targetState.destination.route)
                    if (fromIndex != -1 && toIndex != -1) {
                        if (toIndex > fromIndex) slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(animDuration))
                        else slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(animDuration))
                    } else fadeOut(tween(animDuration))
                }
            ) {
                composable("home") {
                    FonosHomeScreen(
                        navController = navController,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this
                    )
                }
                composable("search") { SearchScreen(navController) }

                composable("active_search") {
                    ActiveSearchScreen(navController = navController)
                }

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
                composable("library") { PlaceholderScreen("Thư viện", navController) }
                composable("personal") { PersonalScreen(navController) }
            }
        }
    }
}