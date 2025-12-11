package com.kienvo.fonosclone.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kienvo.fonosclone.ui.theme.DarkBg
import com.kienvo.fonosclone.widgets.BottomBar

// Model: Màu trên (Top) đậm hơn, Màu dưới (Bottom) nhạt hơn
data class CategoryItemData(
    val title: String,
    val topColor: Color,    // Màu tối (Ở trên)
    val bottomColor: Color, // Màu sáng (Ở dưới)
    val icon: ImageVector
)

@Composable
fun SearchScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    // Màu Header Xanh Than đặc trưng của Fonos
    val headerColor = DarkBg

    val bodyColor = Color(0xFF13161F)

    // [BẢNG MÀU CHUẨN FONOS - ĐÃ CHỈNH SỬA]
    // Quy tắc: Màu đầu là màu tối (Top), Màu sau là màu sáng (Bottom)
    val categories = listOf(
        // Sách nói: Nâu đất đậm -> Nâu nhạt
        CategoryItemData("Sách nói", Color(0xFF5D4037), Color(0xFF8D6E63), Icons.Default.Headphones),

        // PodCourse: Xám xanh đen -> Xám xanh
        CategoryItemData("PodCourse", Color(0xFF263238), Color(0xFF546E7A), Icons.Default.VideoLibrary),

        // Ebook: Xanh lá rừng đậm -> Xanh lá tươi
        CategoryItemData("Ebook", Color(0xFF1B5E20), Color(0xFF43A047), Icons.Default.MenuBook),

        // Tóm tắt: Cam đất -> Vàng nghệ
        CategoryItemData("Tóm Tắt Sách", Color(0xFFE65100), Color(0xFFFFA000), Icons.Default.Book),

        // Thiếu nhi: Đỏ rượu -> Đỏ cam nhạt
        CategoryItemData("Thiếu nhi", Color(0xFFB71C1C), Color(0xFFE57373), Icons.Default.ChildCare),

        // Thiền: Xanh cổ vịt tối -> Xanh ngọc
        CategoryItemData("Thiền", Color(0xFF004D40), Color(0xFF009688), Icons.Default.SelfImprovement),

        // Truyện ngủ: Tím than tối -> Tím huế
        CategoryItemData("Truyện ngủ\n& Nhạc", Color(0xFF311B92), Color(0xFF673AB7), Icons.Default.Nightlight),

        // Podcast: Xanh rêu đậm -> Xanh nõn chuối
        CategoryItemData("Podcast", Color(0xFF33691E), Color(0xFF689F38), Icons.Default.Mic)
    )

    Scaffold(
        containerColor = headerColor,
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            // --- HEADER ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Khám phá", style = MaterialTheme.typography.headlineMedium, color = Color.White, fontWeight = FontWeight.Bold)
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(36.dp))
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Tìm tên sách, tác giả...", color = Color.Gray, fontSize = 14.sp) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
                    shape = RoundedCornerShape(28.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black, focusedTextColor = Color.Black, unfocusedTextColor = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // --- BODY ---
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(bodyColor)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp, 24.dp, 16.dp, 24.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    item(span = { GridItemSpan(2) }) { BigBannerCard() }

                    items(categories) { category ->
                        CategorySmallCard(category)
                    }

                    item(span = { GridItemSpan(2) }) { Spacer(modifier = Modifier.height(20.dp)) }
                }

            }

        }
    }
}

@Composable
fun BigBannerCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(16.dp))
            // Banner Tím: Tối ở trên, Sáng hơn ở dưới góc phải
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFF24135F), Color(0xFF651FFF)),
                    start = androidx.compose.ui.geometry.Offset(0f, 0f),
                    end = androidx.compose.ui.geometry.Offset(1000f, 1000f)
                )
            )
            .clickable { }
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.weight(0.65f).fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text("Mới: Sách Tiếng Anh", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Học tiếng Anh qua sách hay cùng phụ đề song ngữ", color = Color(0xFFD1C4E9), fontSize = 13.sp, lineHeight = 18.sp)
                Spacer(modifier = Modifier.height(12.dp))
                Icon(Icons.Default.Book, contentDescription = null, tint = Color(0xFFEA80FC), modifier = Modifier.size(20.dp))
            }
            Box(modifier = Modifier.weight(0.35f).fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                Icon(Icons.Default.Image, contentDescription = null, tint = Color.White.copy(alpha = 0.2f), modifier = Modifier.size(80.dp))
            }
        }
    }
}

@Composable
fun CategorySmallCard(item: CategoryItemData) {
    Box(
        modifier = Modifier
            .aspectRatio(1.6f)
            .clip(RoundedCornerShape(12.dp)) // Bo góc vừa phải (12.dp) giống Fonos
            // [QUAN TRỌNG] Gradient Dọc: Đậm (Trên) -> Nhạt (Dưới)
            .background(
                Brush.verticalGradient(
                    colors = listOf(item.topColor, item.bottomColor)
                )
            )
            .clickable { }
            .padding(12.dp)
    ) {
        Text(
            text = item.title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            lineHeight = 20.sp,
            modifier = Modifier.align(Alignment.TopStart)
        )

        Icon(
            imageVector = item.icon,
            contentDescription = null,
            // Icon màu trắng mờ, hòa vào nền
            tint = Color.White.copy(alpha = 0.25f),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(44.dp)
        )
    }
}