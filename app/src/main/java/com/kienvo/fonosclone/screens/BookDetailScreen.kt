package com.kienvo.fonosclone.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kienvo.fonosclone.ui.theme.DarkBg
import com.kienvo.fonosclone.ui.theme.Yellow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun BookDetailScreen(
    navController: NavController,
    bookId: String?,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    // --- DỮ LIỆU GIẢ LẬP (MOCK DATA) ---
    // Sau này bạn sẽ dùng bookId để lấy thông tin sách thật từ List
    val mockTitle = "Muôn Kiếp Nhân Sinh"
    val mockAuthor = "Nguyên Phong"
    val mockCover = "https://product.hstatic.net/200000122283/product/bia1-muonkiepnhansinh3-01_d1a246c6abfd4621bed63b8ca3b73ba9_master.jpg"
    val mockDesc = "Một cuốn sách thức tỉnh về luật nhân quả, luân hồi và vị thế của con người trong vũ trụ. Thông qua câu chuyện kỳ lạ của Thomas - một doanh nhân tài chính ở New York, tác giả Nguyên Phong đã vén màn những bí ẩn về kiếp sống..."

    Scaffold(
        containerColor = DarkBg,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) { // Nút Quay lại
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Share */ }) {
                        Icon(Icons.Default.Share, contentDescription = "Share", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { paddingValues ->

        // Box chứa toàn bộ nội dung (để xếp chồng các lớp nền)
        Box(modifier = Modifier.fillMaxSize()) {

            // --- LỚP 1: HÌNH NỀN MỜ (BACKGROUND) ---
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(mockCover).crossfade(true).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(radius = 60.dp) // Làm mờ thật mạnh
                    .background(Color.Black.copy(alpha = 0.4f)) // Phủ thêm lớp đen
            )

            // Gradient đen phủ từ dưới lên để chữ dễ đọc
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, DarkBg),
                            startY = 0f,
                            endY = 1500f // Điều chỉnh độ dài gradient
                        )
                    )
            )

            // --- LỚP 2: NỘI DUNG CHÍNH (Cuộn được) ---
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                // 1. ẢNH BÌA SÁCH (HERO IMAGE)
                with(sharedTransitionScope){
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(mockCover).crossfade(true).build(),
                        contentDescription = "Book Cover",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(200.dp)
                            .height(300.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .sharedElement(
                                sharedContentState = rememberSharedContentState(key = "image-${bookId}"),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                            // Hiệu ứng viền nhẹ
                            .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 2. TÊN SÁCH & TÁC GIẢ
                Text(
                    text = mockTitle,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Tác giả: $mockAuthor",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.LightGray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // 3. CỤM NÚT HÀNH ĐỘNG
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Nút Yêu thích (Tròn)
                    IconButton(
                        onClick = { /* TODO */ },
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color.White.copy(alpha = 0.1f), CircleShape)
                    ) {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = null, tint = Color.White)
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    // Nút PHÁT NGAY (To nhất)
                    Button(
                        onClick = { /* TODO: Chuyển sang màn hình Player */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Yellow),
                        shape = RoundedCornerShape(50), // Bo tròn hoàn toàn
                        modifier = Modifier
                            .height(56.dp)
                            .width(180.dp)
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.Black)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Phát Ngay",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    // Nút Tải về/Khác (Giả lập bằng nút tròn)
                    IconButton(
                        onClick = { /* TODO */ },
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color.White.copy(alpha = 0.1f), CircleShape)
                    ) {
                        // Icon mây tải xuống (hoặc icon khác tùy bạn)
                        Icon(Icons.Default.Share, contentDescription = null, tint = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                // 4. GIỚI THIỆU / MÔ TẢ
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DarkBg) // Nền đen đặc cho phần chữ dưới cùng
                        .padding(24.dp)
                ) {
                    Text(
                        "Giới thiệu",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = mockDesc,
                        color = Color.LightGray,
                        lineHeight = 24.sp,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Justify
                    )

                    // Thêm khoảng trống để lướt hết
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}