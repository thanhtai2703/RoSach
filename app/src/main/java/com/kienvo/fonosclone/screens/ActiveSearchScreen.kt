package com.kienvo.fonosclone.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ActiveSearchScreen(navController: NavController) {
    var query by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    // Dữ liệu giả lập (Giữ nguyên như cũ)
    val hotBooks = listOf(
        Triple("https://bizweb.dktcdn.net/thumb/1024x1024/100/363/455/products/motthoangtarucroonhangian011.jpg?v=1705552591463", "Tính Khí Của Trẻ", "--"),
        Triple("https://images-na.ssl-images-amazon.com/images/I/811PTyrckTL.jpg", "Sapiens Lược Sử", "4.7 (3)"),
        Triple("https://cdn0.fahasa.com/media/catalog/product/i/m/image_195509_1_36793.jpg", "Nhà Giả Kim", "5.0 (2)")
    )

    val topSearches = listOf(
        Triple("https://cdn.hstatic.net/products/200000900535/doc_vi_bat_ky_ai_de_khong_bi_loi_dung_-bia_1__tb_2025__899034494358448295b41a80dc16019e.jpg", "Thiên Nga Đen", "Nassim Nicholas Taleb"),
        Triple("https://cdn0.fahasa.com/media/catalog/product/d/a/da-thit-trong-cuoc-choi.jpg", "Da Thịt Trong Cuộc Chơi", "Nassim Nicholas Taleb"),
        Triple("https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1590656889i/53664287.jpg", "Muôn Kiếp Nhân Sinh", "Nguyên Phong"),
        Triple("https://salt.tikicdn.com/cache/w1200/ts/product/2e/7c/46/40195a63a56dcde10372df37b9909249.jpg", "Tuổi Trẻ Đáng Giá Bao Nhiêu", "Rosie Nguyễn")
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            // --- HEADER SEARCH BAR (Màu xanh than) ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1E2F43)) // Nền xanh than đậm
                    .padding(top = 40.dp, bottom = 12.dp, start = 16.dp, end = 16.dp), // Padding top lớn để tránh status bar
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 1. Ô TÌM KIẾM (Nền trắng, bo tròn)
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Kính lúp (Màu xám)
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Ô nhập liệu
                    Box(modifier = Modifier.weight(1f)) {
                        if (query.isEmpty()) {
                            Text("Tìm tên sách, tác giả...", color = Color.Gray, fontSize = 14.sp)
                        }
                        BasicTextField(
                            value = query,
                            onValueChange = { query = it },
                            singleLine = true,
                            textStyle = MaterialTheme.typography.bodyLarge.copy(
                                color = Color.Black,
                                fontSize = 14.sp
                            ),
                            cursorBrush = SolidColor(Color(0xFF1E2F43)), // Con trỏ màu xanh
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(onSearch = { keyboardController?.hide() }),
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester)
                        )
                    }

                    // Nút Xóa (X) khi có chữ
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

                // 2. NÚT HỦY (Màu trắng)
                Text(
                    text = "Hủy",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable { navController.popBackStack() }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .clip(RoundedCornerShape(15.dp))
                .background(Color(0xFFF5F7F9))
                .padding(horizontal = 16.dp)
        ){

        }
    }
}

// --- ITEM WIDGETS GIỮ NGUYÊN ---

