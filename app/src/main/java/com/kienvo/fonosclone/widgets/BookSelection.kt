package com.kienvo.fonosclone.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kienvo.fonosclone.model.Book

@Composable
fun BookSection(
    title: String,
    books: List<Book>,
    onBookClick: (String) -> Unit = {}
) {
    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        // 1. Tiêu đề kệ (VD: Sách Mới, Sách Bán Chạy)
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = Color.White,
            fontWeight = FontWeight.Medium
        )

        // 2. Danh sách cuộn ngang (LazyRow)
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp) // Căn lề 2 đầu
        ) {
            items(books) { book ->
                BookCard(book = book, onBookClick = onBookClick)
            }
        }
    }
}