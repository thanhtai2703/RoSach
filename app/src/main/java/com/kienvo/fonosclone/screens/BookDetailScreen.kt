package com.kienvo.fonosclone.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kienvo.fonosclone.ui.theme.DarkBg
import com.kienvo.fonosclone.ui.theme.Yellow
import com.kienvo.fonosclone.widgets.ActionCircleButton
import com.kienvo.fonosclone.widgets.AmbienceBottomSheet
import com.kienvo.fonosclone.widgets.AmbienceSliderItem
import com.kienvo.fonosclone.widgets.BookStatItem
import com.kienvo.fonosclone.widgets.ChapterItem
import com.kienvo.fonosclone.widgets.InfoRow
import com.kienvo.fonosclone.widgets.MyDivider
import com.kienvo.fonosclone.widgets.SectionTitle
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun BookDetailScreen(
    navController: NavController,
    bookId: String?,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    // --- MOCK DATA ---
    val mockTitle = "Muôn Kiếp Nhân Sinh"
    val mockAuthor = "Nguyên Phong"
    val mockCover = "https://product.hstatic.net/200000122283/product/bia1-muonkiepnhansinh3-01_d1a246c6abfd4621bed63b8ca3b73ba9_master.jpg"
    val mockDesc = "Một cuốn sách thức tỉnh về luật nhân quả, luân hồi và vị thế của con người trong vũ trụ. Thông qua câu chuyện kỳ lạ của Thomas - một doanh nhân tài chính ở New York, tác giả Nguyên Phong đã vén màn những bí ẩn về kiếp sống..."
    val chapters = listOf(
        "Chương 1: Cuộc gặp gỡ định mệnh tại New York",
        "Chương 2: Những bí ẩn của tiền kiếp",
        "Chương 3: Luật Nhân Quả vận hành thế nào?",
        "Chương 4: Bài học từ nền văn minh Atlantis",
        "Chương 5: Sự thức tỉnh tâm linh",
        "Chương 6: Lời kết và thông điệp"
    )

    // --- STATE ---
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var rainVolume by remember { mutableFloatStateOf(0f) }
    var fireVolume by remember { mutableFloatStateOf(0f) }
    var cafeVolume by remember { mutableFloatStateOf(0f) }

    Scaffold(
        containerColor = DarkBg,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Share, contentDescription = "Share", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { paddingValues ->

        Box(modifier = Modifier.fillMaxSize()) {
            // LAYER 1: BACKGROUND MỜ
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(mockCover).crossfade(true).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(radius = 60.dp)
                    .background(Color.Black.copy(alpha = 0.4f))
            )

            // LAYER 2: NỘI DUNG CHÍNH SCROLL
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding())
                    .verticalScroll(rememberScrollState()), // Scroll cho toàn màn hình
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                // --- TOP SECTION: BÌA SÁCH & ACTION ---
                with(sharedTransitionScope) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(mockCover).crossfade(true).build(),
                        contentDescription = "Book Cover",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(200.dp)
                            .height(300.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                            .sharedElement(
                                sharedContentState = rememberSharedContentState(key = "image-$bookId"),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
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

                // CỤM NÚT ACTION
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ActionCircleButton(icon = Icons.Default.FavoriteBorder)
                    Spacer(modifier = Modifier.width(24.dp))
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = Yellow),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .height(56.dp)
                            .width(180.dp)
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.Black)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Phát Ngay", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    ActionCircleButton(icon = Icons.Default.Tune) { showBottomSheet = true }
                }

                Spacer(modifier = Modifier.height(40.dp))

                // --- INFO SHEET (PHẦN BO TRÒN) ---
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                        .background(Color(0xFF181818))
                        .padding(24.dp)
                ) {
                    // Thanh nắm (Handle)
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(Color.DarkGray)
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    // 1. THỐNG KÊ (Icon Row)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        BookStatItem(Icons.Default.AccessTime, "12h 45p", "Thời lượng")
                        BookStatItem(Icons.Default.Category, "Tâm linh", "Thể loại")
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // 2. BANNER HỘI VIÊN (Màu Be nhạt giống Fonos)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFFFF4E0)) // Màu kem/be
                            .padding(16.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            // Icon lửa/vương miện
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color(0xFFFF6D00), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Diamond, contentDescription = null, tint = Color.White)
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(
                                    "Man88 - Thành viên VIP",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    fontSize = 16.sp
                                )
                                Text(
                                    "Bản lĩnh tỷ phú viên kim cương phải bóng loáng.",
                                    color = Color.DarkGray,
                                    fontSize = 13.sp,
                                    lineHeight = 18.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    MyDivider()
                    Spacer(modifier = Modifier.height(24.dp))

                    // 3. THÔNG TIN THÊM (Giọng đọc, NXB)
                    InfoRow(label = "Giọng đọc", value = "Kiên Cặc Bự")
                    Spacer(modifier = Modifier.height(12.dp))
                    InfoRow(label = "Nhà xuất bản", value = "First News - Trí Việt")
                    Spacer(modifier = Modifier.height(12.dp))
                    InfoRow(label = "Phát hành", value = "15/08/2024")

                    Spacer(modifier = Modifier.height(24.dp))
                    MyDivider()
                    Spacer(modifier = Modifier.height(24.dp))

                    // 4. GIỚI THIỆU
                    SectionTitle(title = "Giới thiệu")
                    Text(
                        text = mockDesc,
                        color = Color.LightGray,
                        lineHeight = 24.sp,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Justify
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                    MyDivider()
                    Spacer(modifier = Modifier.height(24.dp))

                    // 5. DANH SÁCH CHƯƠNG (Chapter List)
                    SectionTitle(title = "Danh sách chương")
                    // Dùng ForEach thay vì LazyColumn vì đang nằm trong Column scrollable
                    chapters.forEachIndexed { index, chapterName ->
                        ChapterItem(index = index + 1, name = chapterName)
                    }

                    // Spacer lớn cuối cùng
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }

            // --- BOTTOM SHEET MIXER ---
            if (showBottomSheet) {
                AmbienceBottomSheet(
                    sheetState = sheetState,
                    onDismiss = { showBottomSheet = false },
                    onAiClick = { scope.launch { rainVolume = 0.6f; fireVolume = 0.2f } },
                    rainVol = rainVolume, onRainChange = { rainVolume = it },
                    fireVol = fireVolume, onFireChange = { fireVolume = it },
                    cafeVol = cafeVolume, onCafeChange = { cafeVolume = it }
                )
            }
        }
    }
}
