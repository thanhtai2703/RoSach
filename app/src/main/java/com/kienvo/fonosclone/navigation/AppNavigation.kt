package com.kienvo.fonosclone.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kienvo.fonosclone.screens.FonosHomeScreen
import com.kienvo.fonosclone.screens.PersonalScreen
import com.kienvo.fonosclone.ui.theme.DarkBg

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceholderScreen(title: String, navController: NavHostController) {
    Scaffold(
        containerColor = DarkBg,
        bottomBar = {
            com.kienvo.fonosclone.widgets.BottomBar(navController)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        title,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBg
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Màn hình $title\n(Sẽ được phát triển sau)",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        }
    }
}
