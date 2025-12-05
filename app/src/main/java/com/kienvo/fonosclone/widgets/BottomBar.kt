package com.kienvo.fonosclone.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.kienvo.fonosclone.ui.theme.DarkBg
import com.kienvo.fonosclone.ui.theme.Yellow

@Composable
fun BottomBar(){
    // State lưu tab đang chọn (0: Trang chủ, 1: Tìm kiếm...)
    var selectedItem by remember { mutableIntStateOf(0) }

    val items = listOf("Trang chủ", "Tìm kiếm", "Thư viện", "Cá nhân")
    val icons = listOf(
        Icons.Default.Home,
        Icons.Default.Search,
        Icons.Default.LibraryBooks, // Bạn có thể đổi icon khác
        Icons.Default.Person
    )

    NavigationBar(
        containerColor = DarkBg, // Nền đen tiệp màu với app
        contentColor = Color.White
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(icons[index], contentDescription = item) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                // Custom màu sắc theo RoPhim Style
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = DarkBg, // Icon khi chọn (màu tối trên nền vàng)
                    selectedTextColor = Yellow, // Chữ khi chọn
                    indicatorColor = Yellow,    // Cái vệt sáng nền khi chọn
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}
