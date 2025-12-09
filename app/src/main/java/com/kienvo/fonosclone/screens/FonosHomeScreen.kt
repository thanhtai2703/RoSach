package com.kienvo.fonosclone.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kienvo.fonosclone.model.getBooks
import com.kienvo.fonosclone.model.getHomeScreenData
import com.kienvo.fonosclone.ui.theme.DarkBg
import com.kienvo.fonosclone.ui.theme.Yellow
import com.kienvo.fonosclone.widgets.BookSection
import com.kienvo.fonosclone.widgets.BottomBar
import com.kienvo.fonosclone.widgets.FonosCarousel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun FonosHomeScreen(
    navController: NavController? = null,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    // Lấy danh sách các danh mục sách (Data động)
    val homeCategories = remember { getHomeScreenData() }

    // Lấy list sách riêng để hiển thị Carousel (Banner)
    val carouselBooks = remember { getBooks() }

    // State quản lý hình nền thay đổi theo carousel
    val (currentBgUrl, setCurrentBgUrl) = remember { mutableStateOf(carouselBooks.firstOrNull()?.coverUrl) }

    // State giả lập trạng thái đăng nhập
    val isLoggedIn = remember { mutableStateOf(false) }
    val userAvatarUrl = "https://icons.veryicon.com/png/o/miscellaneous/common-icons-31/default-avatar-2.png"

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
                            Button(
                                onClick = {
                                    isLoggedIn.value = true
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Yellow),
                                shape = RoundedCornerShape(30.dp)
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

        // CHUYỂN ĐỔI QUAN TRỌNG: Dùng LazyColumn thay vì Column + verticalScroll
        // LazyColumn tối ưu hơn cho danh sách dài và cho phép sinh mục tự động
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 0.dp)
        ) {

            // === ITEM 1: HEADER (CHỨA HÌNH NỀN + CAROUSEL) ===
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(650.dp)
                ) {
                    // 1. HÌNH NỀN
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

                    // 2. GRADIENT PHỦ
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        DarkBg.copy(alpha = 0.2f),
                                        DarkBg.copy(alpha = 0.8f),
                                        DarkBg
                                    )
                                )
                            )
                    )

                    // 3. CAROUSEL
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = paddingValues.calculateTopPadding())
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        FonosCarousel(
                            books = carouselBooks,
                            onBookClick = { bookId ->
                                navController?.navigate("detail/$bookId")},
                            onCurrentPosterChanged = { url -> setCurrentBgUrl(url) },
                            sharedTransitionScope = sharedTransitionScope,
                            animatedVisibilityScope = animatedVisibilityScope)
                    }
                }
            }

            // === ITEM 2: PHẦN NÚT BẤM & THÔNG TIN ===
            item {
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
                        // Nút 1: Phát Ngay
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

                        // Nút 2: Chi Tiết
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
            }

            // === ITEM 3: DANH SÁCH CÁC DANH MỤC (TỰ ĐỘNG SINH) ===
            // Dùng hàm items() của LazyColumn để lặp qua list homeCategories
            items(homeCategories) { category ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-80).dp)
                ) {
                    BookSection(
                        title = category.title,
                        books = category.books,
                        onBookClick = { bookId ->
                            navController?.navigate("detail/$bookId")
                        },
                        sharedTransitionScope = sharedTransitionScope,
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}