package com.kienvo.fonosclone.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kienvo.fonosclone.ui.theme.DarkBg
import com.kienvo.fonosclone.widgets.BottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceholderScreen(
    title: String,
    navController: NavController
) {
    Scaffold(
        containerColor = DarkBg, // Nền tối đồng bộ

        // 1. Tái sử dụng BottomBar (Để người dùng không bị kẹt ở màn hình này)
        bottomBar = {
            BottomBar(navController = navController)
        },

        // 2. TopBar đơn giản
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBg // Màu nền TopBar trùng với nền App
                )
            )
        }
    ) { paddingValues ->
        // 3. Nội dung thông báo ở giữa
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Tính năng \"$title\"\nđang được phát triển 🔨",
                color = Color.Gray,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                lineHeight = 28.sp
            )
        }
    }
}