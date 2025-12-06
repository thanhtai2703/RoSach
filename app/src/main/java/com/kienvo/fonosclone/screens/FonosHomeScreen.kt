package com.kienvo.fonosclone.screens

import android.R.attr.action
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kienvo.fonosclone.model.getBooks
import com.kienvo.fonosclone.model.getDetectiveBooks
import com.kienvo.fonosclone.model.getHealingBooks
import com.kienvo.fonosclone.model.getPopularBooks
import com.kienvo.fonosclone.ui.theme.DarkBg
import com.kienvo.fonosclone.widgets.BookSection
import com.kienvo.fonosclone.widgets.BottomBar
import com.kienvo.fonosclone.widgets.FonosCarousel
import androidx.navigation.NavController
import com.kienvo.fonosclone.ui.theme.Yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FonosHomeScreen(navController: NavController? = null) {
    val books = getBooks()
    val booksPopular = getPopularBooks()
    val booksHealing = getHealingBooks()
    val booksDetective = getDetectiveBooks()
    val scrollState = rememberScrollState()
    val (currentBgUrl, setCurrentBgUrl) = remember { mutableStateOf(books.firstOrNull()?.coverUrl) }
    val isLoggedIn = remember { mutableStateOf(false) }
    val userAvatarUrl =
        "https://icons.veryicon.com/png/o/miscellaneous/common-icons-31/default-avatar-2.png"

    // [MỚI] Định nghĩa Gradient cho Top Bar: Đen mờ (0.7) -> Trong suốt
    val topBarGradient = Brush.verticalGradient(
        colors = listOf(
            Color.Black.copy(alpha = 0.7f),
            Color.Transparent
        )
    )

    // Scaffold có nền đen tuyệt đối
    Scaffold(
        containerColor = DarkBg,
        bottomBar = {
            navController?.let { BottomBar(it) }
        },
        topBar = {
            TopAppBar(
                modifier = Modifier.background(topBarGradient),
                title = {
                    Column(modifier = Modifier.padding(start = 20.dp)) {
                        Text(
                            "Fonos",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 26.sp
                        )
                        Text(
                            "Audio Book Application",
                            color = Color.LightGray,
                            fontWeight = FontWeight.Medium,
                            fontSize = 15.sp
                        )
                    }
                },
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        // 1. Nút Đăng nhập (Chỉ hiện khi CHƯA đăng nhập)
                        if (!isLoggedIn.value) {
                            Button (
                                onClick = {
                                    isLoggedIn.value = true
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                shape = RoundedCornerShape(15.dp)
                            ) {
                                Text(
                                    text = "Đăng nhập",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(15.dp))
                        }

                        // 2. Avatar User (Luôn hiển thị)
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.2f))
                                .clickable {
                                    // Click vào avatar để test đăng xuất
                                    if (isLoggedIn.value) isLoggedIn.value = false
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (isLoggedIn.value) {
                                // Nếu đã login -> Load ảnh
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(userAvatarUrl)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = "User Avatar",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else {
                                // Nếu chưa login -> Icon mặc định
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Default Avatar",
                                    tint = Color.White,
                                    modifier = Modifier.padding(6.dp)
                                )
                            }
                        }
                    }
                },
                // Quan trọng: Màu trong suốt để nhìn xuyên thấu xuống hình nền bên dưới
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent,
                    titleContentColor = Color.White
                ),
            )


        }) { paddingValues ->

        // Cột cuộn chính
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState) // Cho phép cuộn toàn bộ nội dung
        ) {

            // === PHẦN HEADER (CHỨA HÌNH NỀN + CAROUSEL) ===
            // Box này sẽ cuộn đi khi người dùng lướt xuống
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(650.dp) // Chiều cao khu vực Header
            ) {
                // 1. HÌNH NỀN (Nằm dưới cùng của Header)
                currentBgUrl?.let { url ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(url).crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(radius = 50.dp)
                            .background(Color.Black.copy(alpha = 0.3f))
                    )
                }

                // 2. GRADIENT PHỦ (Hòa tan đáy ảnh vào nền đen của Scaffold)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    DarkBg.copy(alpha = 0.2f),
                                    DarkBg.copy(alpha = 0.8f),
                                    DarkBg // Đáy của Header phải trùng màu với nền Scaffold
                                )
                            )
                        )
                )

                // 3. CAROUSEL (Nội dung chính của Header)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = paddingValues.calculateTopPadding()) // Né cái TopBar ra
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    FonosCarousel(
                        books = books,
                        onBookClick = { println("Click: $it") },
                        onCurrentPosterChanged = { url -> setCurrentBgUrl(url) })
                }
            }

            // === [MỚI] PHẦN NÚT BẤM & THÔNG TIN ===
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-120).dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Hàng Nút Bấm
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Nút 1: Phát Ngay (Màu Vàng RoPhim)
                    Button(
                        onClick = { /* TODO */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Yellow),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .width(160.dp)
                            .height(48.dp)
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.Black)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Nghe Ngay", color = Color.Black, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Nút 2: Thông tin / Thêm vào DS (Màu Xám)
                    Button(
                        onClick = { /* TODO */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .width(160.dp)
                            .height(48.dp)
                    ) {
                        Icon(Icons.Default.Info, contentDescription = null, tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Chi Tiết", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }

            // === PHẦN DANH SÁCH BÊN DƯỚI (NỀN ĐEN) ===
            // Khi cuộn, phần này sẽ trồi lên
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    // Dịch lên một chút (-50dp) để đè nhẹ lên phần mờ của Header tạo cảm giác liền mạch
                    .padding(top = 0.dp)
                    .offset(y = (-80).dp)
            ) {
                BookSection(title = "Top Thịnh Hành", books = booksPopular)
                BookSection(title = "Sách Chữa Lành", books = booksHealing)
                BookSection(title = "Sách Trinh Thám", books = booksDetective)
                BookSection(title = "Sách Học thuật", books = books)

                Spacer(modifier = Modifier.height(100.dp)) // Padding đáy
            }
        }
    }
}