package com.kienvo.fonosclone.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kienvo.fonosclone.model.Book
import com.kienvo.fonosclone.ui.theme.OrangeTag
import com.kienvo.fonosclone.ui.theme.RedTag
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun FonosCarousel(
    books: List<Book>,
    onBookClick: (String) -> Unit,
    onCurrentPosterChanged: (String) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    if (books.isEmpty()) return

    val realCount = books.size
    val infiniteCount = Int.MAX_VALUE
    val startIndex = infiniteCount / 2
    val pagerState = rememberPagerState(initialPage = startIndex) { infiniteCount }

    androidx.compose.runtime.LaunchedEffect(pagerState) {
        androidx.compose.runtime.snapshotFlow { pagerState.currentPage }.collect { pageIndex ->
            val realIndex = pageIndex % realCount
            onCurrentPosterChanged(books[realIndex].coverUrl)
        }
    }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val horizontalPadding = (screenWidth * 0.22f)

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = horizontalPadding),
        modifier = Modifier
            .fillMaxWidth()
            .height(420.dp)
    ) { pageIndex ->

        val realIndex = pageIndex % realCount
        val book = books[realIndex]
        val pageOffset = (pagerState.currentPage - pageIndex) + pagerState.currentPageOffsetFraction

        val scale = lerp(0.85f, 1f, 1f - pageOffset.absoluteValue.coerceIn(0f, 1f))
        val alpha = lerp(0.5f, 1f, 1f - pageOffset.absoluteValue.coerceIn(0f, 1f))


        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            // CỘT NỘI DUNG CHÍNH
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(200.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    }

                    .clickable { onBookClick(book.id) }
            ) {
                // 1. BOX ẢNH + NÚT PLAY
                Box(
                    modifier = Modifier
                        .height(320.dp)
                        .fillMaxWidth()
                ) {
                    // Ảnh bìa
                    Card(
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
                        elevation = CardDefaults.cardElevation(10.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        with(sharedTransitionScope){
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(book.coverUrl)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                                    .sharedElement(
                                        sharedContentState = rememberSharedContentState(key = "image-${book.id}"),
                                        animatedVisibilityScope = animatedVisibilityScope
                                    )
                            )
                        }
                    }

                    // Nút Play
                    androidx.compose.animation.AnimatedVisibility(
                        visible = pageOffset.absoluteValue < 0.3f,
                        enter = scaleIn(animationSpec = tween(300)) + fadeIn(tween(300)),
                        exit = scaleOut(animationSpec = tween(300)) + fadeOut(tween(300)),
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 12.dp, bottom = 12.dp)
                    ) {
                        CustomPlayButton(
                            size = 45.dp,
                            onClick = { println("Play: ${book.title}") }
                        )
                    }
                }

                // 2. TAG TÁC GIẢ
                androidx.compose.animation.AnimatedVisibility(
                    visible = pageOffset.absoluteValue < 0.3f,
                    enter = slideInVertically(initialOffsetY = { -it }, animationSpec = tween(300)) +
                            expandVertically(expandFrom = Alignment.Top, animationSpec = tween(300)) +
                            fadeIn(tween(300)),
                    exit = slideOutVertically(targetOffsetY = { -it }, animationSpec = tween(300)) +
                            shrinkVertically(shrinkTowards = Alignment.Top, animationSpec = tween(300)) +
                            fadeOut(tween(300))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RectangleShape)
                            .background(
                                brush = Brush.horizontalGradient(listOf(RedTag, OrangeTag)),
                                shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "🔥 ${book.author.uppercase()}",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}