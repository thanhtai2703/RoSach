package com.kienvo.fonosclone.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ActiveSearchScreen(navController: NavController) {
    var query by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    // Màu header giống Fonos (Xanh than đậm)
    val headerColor = Color(0xFF0F1015)
    // Màu nền box bên dưới (Trắng hoặc Xám rất nhạt)
    val contentBoxColor = Color(0xFF13161F)

    // Tự động focus vào ô tìm kiếm và bật bàn phím khi màn hình được mở
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Scaffold(
        containerColor = headerColor, // Nền tổng thể là màu header để không bị lẹm màu
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(headerColor)
                    // [SỬA 1] Tăng padding top lên 48.dp để tránh che ngày/giờ hệ thống
                    .padding(top = 48.dp, bottom = 12.dp, start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 1. Ô Input (Nền trắng)
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp) // Chiều cao thanh tìm kiếm
                        // [SỬA 2] Bo tròn 24.dp (hình viên thuốc) cho giống ảnh mẫu
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color.White)
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Icon Kính Lúp
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // TextField tùy biến
                    Box(modifier = Modifier.weight(1f)) {
                        if (query.isEmpty()) {
                            Text(
                                text = "Tìm tên sách, tác giả...",
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                        BasicTextField(
                            value = query,
                            onValueChange = { query = it },
                            singleLine = true,
                            textStyle = androidx.compose.ui.text.TextStyle(
                                color = Color.Black,
                                fontSize = 14.sp
                            ),
                            cursorBrush = SolidColor(headerColor), // Con trỏ màu xanh
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(onSearch = { keyboardController?.hide() }),
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester)
                        )
                    }

                    // Nút Xóa (chỉ hiện khi có chữ)
                    if (query.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear",
                            tint = Color.Gray,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable { query = "" }
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                // 2. Nút Hủy (Bên ngoài)
                Text(
                    text = "Hủy",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable {
                        // Logic hủy: Quay lại màn hình trước
                        navController.popBackStack()
                    }
                )
            }
        }


    ) { paddingValues ->

        // --- BOX NỘI DUNG BÊN DƯỚI ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Tránh đè lên header
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            // Box bo góc trượt lên giống hình
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)) // Bo 2 góc trên
                    .background(contentBoxColor) // Màu nền trắng/xám nhạt
            ) {
                // RỖNG - Không bỏ gì vào đây theo yêu cầu
            }
        }
    }
}